package com.example.practice2_test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {

    private MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText numeTxt = findViewById(R.id.numeTxt);
        EditText varstaTxt = findViewById(R.id.varstaTxt);
        EditText echipaTxt = findViewById(R.id.echipaTxt);
        EditText idTxt = findViewById(R.id.idTxt);
        Spinner stagiuCariera = findViewById(R.id.stagiucarereTxt);

        Intent it = getIntent();
        if (it.hasExtra("jucator")){
            Jucator j = it.getParcelableExtra("jucator");
            numeTxt.setText(j.getNumeComplet());
            varstaTxt.setText(String.valueOf(j.getVarsta()));
            echipaTxt.setText(j.getEchipa());
            idTxt.setText(String.valueOf(j.getId()));
            stagiuCariera.setSelection(2);
        }



        Button adaugat = findViewById(R.id.jucatorAdaugaBtn);

        adaugat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nume = numeTxt.getText().toString();
                String echipa = echipaTxt.getText().toString();
                int id = Integer.parseInt(idTxt.getText().toString());
                int varsta = Integer.parseInt(varstaTxt.getText().toString());
                String stadiu = stagiuCariera.getSelectedItem().toString();

                Jucator j = new Jucator(id,nume,echipa,varsta,stadiu);
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "Jucator").build();
                        db.getInterface().insertJucator(j);
                    }
                });


                Intent it = new Intent();
                it.putExtra("jucator", j);
                setResult(-1, it);
                finish();
            }
        });
    }
}