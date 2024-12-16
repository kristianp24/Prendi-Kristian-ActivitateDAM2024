package com.example.test2_practice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CutremuriActivity extends AppCompatActivity {

    String urlAPI = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2020-01-01&endtime=2020-01-02";
    List<Earthquake> earthquakeList;
    List<Earthquake> dateluateBD;
    EarthquakeDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cutremuri);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        earthquakeList = new ArrayList<>();
        Button preiaDateBtn = findViewById(R.id.preiaDateBtn);
        Button salveazaBD = findViewById(R.id.salveazaBDbtn);
        Button preiaDateBD = findViewById(R.id.preiaBDbtn);

        preiaDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(urlAPI);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            Scanner scanner = new Scanner(conn.getInputStream());
                            StringBuilder builder = new StringBuilder();

                            while (scanner.hasNext()){
                                builder.append(scanner.nextLine());
                            }

                            JSONObject object = new JSONObject(builder.toString());
                            JSONArray featureArray = object.getJSONArray("features");
                            for (int i=0; i<featureArray.length();i++){
                                JSONObject featureObject = featureArray.getJSONObject(i);
                                String id = featureObject.getString("id");
                                JSONObject propertiesObject = featureObject.getJSONObject("properties");
                                Double magnitude = propertiesObject.getDouble("mag");
                                String place = propertiesObject.getString("place");
                                int tsunami = propertiesObject.getInt("tsunami");
                                earthquakeList.add(new Earthquake(id, place, magnitude, tsunami));
                            }


                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (earthquakeList.isEmpty())
                                    Toast.makeText(CutremuriActivity.this, "Lista goala", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(CutremuriActivity.this, "Datele luate din API!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });

        salveazaBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        db = Room.databaseBuilder(getApplicationContext(), EarthquakeDatabase.class, "Earthquake").build();
                        db.getInterface().insertData(earthquakeList);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CutremuriActivity.this, "Datele salvate in BD", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                });
            }
        });

        preiaDateBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateluateBD = new ArrayList<>();
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        db = Room.databaseBuilder(getApplicationContext(), EarthquakeDatabase.class, "Earthquake").build();
                        dateluateBD = db.getInterface().getEarthquakes();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Intent it = new Intent(getApplicationContext(), AfisareCutremuriActivity.class);
                                it.putParcelableArrayListExtra("cutremuri", (ArrayList<? extends Parcelable>) dateluateBD);
                                startActivity(it);
                            }
                        });
                    }



                });
            }
        });
    }
}