package com.example.expense_tracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AfiseazaListaImaginiActivity extends AppCompatActivity {

    List<Bitmap> images;
    List<Imagine> myImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afiseaza_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuri = new ArrayList<>();
        images = new ArrayList<>();
        linkuri.add("https://w7.pngwing.com/pngs/38/708/png-transparent-car-mercedes-car-love-compact-car-vehicle-thumbnail.png");
        linkuri.add("https://cdn.pixabay.com/photo/2012/05/29/00/43/car-49278_640.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuri){
                    try {
                        URL url = new URL(link);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = conn.getInputStream();
                        images.add(BitmapFactory.decodeStream(inputStream));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myImages = new ArrayList<>();
                            for (Bitmap img : images){
                                myImages.add(new Imagine(img, "Masina mea", "https://w7.pngwing.com/pngs/38/708/png-transparent-car-mercedes-car-love-compact-car-vehicle-thumbnail.png"));

                            }

                            ListView lv = findViewById(R.id.lvImagini);
                            CustomAdapterPoze myAdapter = new CustomAdapterPoze(myImages, getApplicationContext(), R.layout.imagini_row);
                            lv.setAdapter(myAdapter);
                        }
                    });
                }
            }
        });
    }
}