package com.example.practice2_test1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AfiseasaPreferatiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afiseasa_preferati);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Map<String,String> jucatorii = new HashMap<>();
        List<String> jucatoriAfisati = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences("jucatoriFavoriti", MODE_PRIVATE);
        jucatorii = (Map<String, String>) sp.getAll();
        for (String value : jucatorii.values()){
            jucatoriAfisati.add(value);
        }

        ListView lv = findViewById(R.id.lvPreferati);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, jucatoriAfisati);
        lv.setAdapter(adapter);

    }
}