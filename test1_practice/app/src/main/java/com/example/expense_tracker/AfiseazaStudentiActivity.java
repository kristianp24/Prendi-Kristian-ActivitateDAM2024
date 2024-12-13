package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AfiseazaStudentiActivity extends AppCompatActivity {

    List<Student> studenti;
    MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afiseaza_studenti);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView studLv = findViewById(R.id.studentLv);
        studenti = new ArrayList<>();
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "Student").build();
        //Intent it = getIntent();
        Executor exec = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        exec.execute(new Runnable() {
            @Override
            public void run() {
                studenti.add(db.getInterface().getStudent(2));

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        StudentAdapter adapter = new StudentAdapter(studenti, getApplicationContext(), R.layout.row);
                        studLv.setAdapter(adapter);
                    }
                });
            }
        });


//        ArrayAdapter<Student> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, studenti);
//        studLv.setAdapter(adapter);

    }
}