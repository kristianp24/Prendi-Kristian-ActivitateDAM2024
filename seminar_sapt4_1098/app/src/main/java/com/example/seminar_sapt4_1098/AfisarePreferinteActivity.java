package com.example.seminar_sapt4_1098;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AfisarePreferinteActivity extends AppCompatActivity {

    private List<String> obiectePreferate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afisare_preferinte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv = findViewById(R.id.lvPreferinte);
        obiectePreferate = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, obiectePreferate);

        SharedPreferences sp = getSharedPreferences("obiecteFavorite", MODE_PRIVATE);
        Map<String, String> entries = (Map<String, String>) sp.getAll();

        for(String entry : entries.values()){
            obiectePreferate.add(entry);
        }

        lv.setAdapter(adapter);
    }
}