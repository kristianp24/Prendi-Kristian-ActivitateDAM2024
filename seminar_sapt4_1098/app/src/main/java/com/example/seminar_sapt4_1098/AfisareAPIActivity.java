package com.example.seminar_sapt4_1098;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.JsonToken;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AfisareAPIActivity extends AppCompatActivity {

    private String httpAdress = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=kdqZdP3uemoSkApNFrcWqQcP84OcQb02&q=";
    private String httpLocation = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/";
    private String metric = "?apikey=kdqZdP3uemoSkApNFrcWqQcP84OcQb02&metric=true";
    private String cheie = "";
    private String Date ="";
    private String tempMin = "";
    private String tempMax = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_apiactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button btn = findViewById(R.id.adaugatemp);
        EditText orasTxt =  findViewById(R.id.orasTXT);
        TextView tv = findViewById(R.id.afisareTemp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Executor executor =  Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());
                String oras = orasTxt.getText().toString();
                httpAdress += oras;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(httpAdress);
                            HttpURLConnection con = (HttpURLConnection) url.openConnection();
                            if (con.getResponseCode() == 200)
                            {
                                StringBuilder builder = new StringBuilder();

                                Scanner scanner = new Scanner(con.getInputStream());
                                if(scanner.hasNext()){
                                    builder.append(scanner.nextLine());
                                }
                                JSONArray array = new JSONArray(builder.toString());
                                JSONObject obj = array.getJSONObject(0);
                                cheie = obj.getString("Key");

                                httpLocation = httpLocation + cheie + metric;
                                URL url2 = new URL(httpLocation);
                                HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                                if (con2.getResponseCode() == 200){
                                    StringBuilder builder2 = new StringBuilder();

                                    Scanner scanner2 = new Scanner(con2.getInputStream());
                                    if(scanner2.hasNext()){
                                        builder2.append(scanner2.nextLine());
                                    }
                                    JSONObject dailyForecast = new JSONObject(builder2.toString());
                                    JSONArray arrayForecast = dailyForecast.getJSONArray("DailyForecasts");
                                    JSONObject day = arrayForecast.getJSONObject(0);
                                    Date = day.getString("Date");
                                    JSONObject tempObj = day.getJSONObject("Temperature");
                                    JSONObject minObj = tempObj.getJSONObject("Minimum");
                                    tempMin = String.valueOf(minObj.getDouble("Value"));
                                    JSONObject maxObj = tempObj.getJSONObject("Maximum");
                                    tempMax = String.valueOf(maxObj.getDouble("Value"));


                                }

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
                                tv.setText("Data: "+Date+" Temperatura min in C:"+tempMin+" Temperatura Max in C:"+tempMax);
                            }
                        });

                    }

                });
            }
        });


    }
}