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
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    String urlLink = "http://universities.hipolabs.com/search?name=middle";
    String urlLinkShows = "http://api.tvmaze.com/search/shows?q=postman";
    ShowsDatabase db;
    int aux = 0;
    List<University> universities;
    List<TvShow> shows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            universities = new ArrayList<>();
            shows = new ArrayList<>();
            Executor exec = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.myLooper());
            Button btn = findViewById(R.id.button1);
//            exec.execute(new Runnable() {
//                @Override
//                public void run() {
//                    URL url = null;
//                    try {
//                        url = new URL(urlLink);
//                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                        if (conn.getResponseCode() == 200){
//
//                            InputStream stream = conn.getInputStream();
//                            Scanner scanner = new Scanner(stream);
//                            StringBuilder builder = new StringBuilder();
//
//                            if (scanner.hasNext()){
//                                builder.append(scanner.nextLine());
//                            }
//
//                            JSONArray array = new JSONArray(builder.toString());
//                            for (int i=0; i<array.length(); i++){
//                                JSONObject object = array.getJSONObject(i);
//                                String name = object.getString("name");
//                                String code = object.getString("alpha_two_code");
//                                //Object state = JSONObject.NULL;
//                                String webPage = object.getJSONArray("domains").getString(0);
//                                universities.add(new University(name, code, webPage));
//                            }
//                            aux = 1;
//
//                        }
//                    } catch (MalformedURLException e) {
//                        throw new RuntimeException(e);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (aux == 1){
//                                Toast.makeText(getApplicationContext(), "Datele preluate", Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                                Toast.makeText(getApplicationContext(), "Api is not good", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//
//                }
//            });

           exec.execute(new Runnable() {
               @Override
               public void run() {
                   try {
                       URL url = new URL(urlLinkShows);
                       HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                       StringBuilder builder = new StringBuilder();
                       Scanner scanner = new Scanner(conn.getInputStream());
                       if (scanner.hasNext()){
                           builder.append(scanner.nextLine());
                       }

                       JSONArray array = new JSONArray(builder.toString());
                       for (int i = 0;i<array.length();i++){
                           JSONObject object = array.getJSONObject(i);
                           Double score = object.getDouble("score");
                           Float scoreF = score.floatValue();

                           JSONObject showObject = object.getJSONObject("show");
                           int id = showObject.getInt("id");
                           String name = showObject.getString("name");
                           JSONArray genresArray = showObject.getJSONArray("genres");
                           List<String> genres = new ArrayList<>();
                           for (int j=0;j<genresArray.length();j++){
                               genres.add(genresArray.getString(j));
                           }
                           String generesString = "";
                           for (int k=0;k<genresArray.length();k++){
                               generesString += genresArray.getString(k);
                               generesString += ",";
                           }
                           String date = showObject.getString("premiered");
                           int year = Integer.parseInt(date.split("-")[0]);
                           int month = Integer.parseInt(date.split("-")[1]);
                           int day = Integer.parseInt(date.split("-")[2]);
                           Date premiered = new Date(year, month, day);
                           long premieredLong = premiered.getTime();
                           shows.add(new TvShow(scoreF, id, name, generesString, premieredLong));

                       }
//                       db = Room.databaseBuilder(getApplicationContext(), ShowsDatabase.class, "TvShows").build();
//                       db.getInterface().insertShows(shows);

                       aux = 1;
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
                           if (aux == 1)
                               Toast.makeText(MainActivity.this, "Datele preluate si inserate in baza de date!", Toast.LENGTH_SHORT).show();
                       }
                   });

               }
           });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(getApplicationContext(), AfisareDateAPIActivity.class);
                    it.putParcelableArrayListExtra("shows", (ArrayList<? extends Parcelable>) shows);
                    startActivity(it);
                }
            });





    }
}