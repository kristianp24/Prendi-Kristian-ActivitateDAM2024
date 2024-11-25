package com.example.seminar_sapt4_1098;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AfisareImaginiActivity extends AppCompatActivity {

    private List<Bitmap> imagini;
    private List<Imagini> domeniuImagini;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuri = new ArrayList<>();
        imagini = new ArrayList<>();
        linkuri.add("https://w7.pngwing.com/pngs/38/708/png-transparent-car-mercedes-car-love-compact-car-vehicle-thumbnail.png");
        linkuri.add("https://cdn.pixabay.com/photo/2012/05/29/00/43/car-49278_640.jpg");

        Executor executor =  Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuri){
                    HttpURLConnection conn = null;
                    try{
                        URL url = new URL(link);
                        conn = (HttpURLConnection) url.openConnection();
                        InputStream stream = conn.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(stream));
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!imagini.isEmpty()){
                            domeniuImagini = new ArrayList<>();
                            domeniuImagini.add(new Imagini("Mercedes", imagini.get(0), "https://www.mbusa.com/content/dam/mb-nafta/us/myco/my24/amg-gt-class/2-door/all-vehicles/2024-AMG-GT55-COUPE-AVP-DR.png"));
                            domeniuImagini.add(new Imagini("Bugatti", imagini.get(1), "https://cdn.pixabay.com/photo/2012/05/29/00/43/car-49278_640.jpg"));

                            ListView lv = findViewById(R.id.imaginiLV);
                            ImageAdapter adapter = new ImageAdapter(domeniuImagini, getApplicationContext(), R.layout.imagini_row);
                            lv.setAdapter(adapter);

                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                      Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                                      it.putExtra("link", domeniuImagini.get(i).getLink());
                                      startActivity(it);
                                }
                            });
                        }
                    }
                });
            }
        });






    }
}