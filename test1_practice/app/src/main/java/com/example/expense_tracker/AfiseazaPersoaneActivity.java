package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class AfiseazaPersoaneActivity extends AppCompatActivity {

    List<Persoana> persoanaList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afiseaza_persoane);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent it = getIntent(); // pt ca astepti ceva de la o alta activitate
        persoanaList = it.getParcelableArrayListExtra("lista");

        ListView lv = findViewById(R.id.listaPersoane);
        ArrayAdapter<Persoana> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,persoanaList);
        lv.setAdapter(adapter);

    }
}