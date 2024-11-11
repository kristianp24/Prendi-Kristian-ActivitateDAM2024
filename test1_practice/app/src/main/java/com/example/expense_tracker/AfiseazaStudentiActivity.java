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

public class AfiseazaStudentiActivity extends AppCompatActivity {

    List<Student> studenti;
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
        Intent it = getIntent();
        studenti = it.getParcelableArrayListExtra("studenti");

//        ArrayAdapter<Student> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, studenti);
//        studLv.setAdapter(adapter);
        StudentAdapter adapter = new StudentAdapter(studenti, getApplicationContext(), R.layout.row);
        studLv.setAdapter(adapter);
    }
}