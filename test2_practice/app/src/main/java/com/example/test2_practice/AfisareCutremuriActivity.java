package com.example.test2_practice;

import android.content.Intent;
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
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AfisareCutremuriActivity extends AppCompatActivity {

    List<Earthquake> earthquakeList;
    EarthquakeDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_cutremuri);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        earthquakeList = it.getParcelableArrayListExtra("cutremuri");

        ListView lv = findViewById(R.id.lvEarthqk);
        EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(), earthquakeList, R.layout.my_row);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        db = Room.databaseBuilder(getApplicationContext(), EarthquakeDatabase.class, "Earthquake").build();
                        db.getInterface().deleteFromDb(earthquakeList.get(i));

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });

                    }


                });
                return false;
            }
        });

    }
}