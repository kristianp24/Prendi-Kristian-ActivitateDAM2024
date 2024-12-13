package com.example.practice2_test1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Jucator> jucatori = null;
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

        Button adaugaJucator = findViewById(R.id.icarcaJucatorBtn);
        Button afiseazaJucatori = findViewById(R.id.afiseazaJucatoriBtn);
        Button afiseazaPreferintele  =findViewById(R.id.buttonPreferinte);

        jucatori = new ArrayList<>();

        afiseazaPreferintele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), AfiseasaPreferatiActivity.class);
                startActivity(it);
            }
        });
        afiseazaJucatori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), ListaJucatoriActivity.class);
                it.putParcelableArrayListExtra("jucatori", (ArrayList<? extends Parcelable>) jucatori);
                startActivity(it);
            }
        });


        adaugaJucator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), MainActivity2.class);
                startActivityForResult(it, 133);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 133){
           Jucator jucator = data.getParcelableExtra("jucator");
            jucatori.add(jucator);
            Toast.makeText(getApplicationContext(), jucator.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}