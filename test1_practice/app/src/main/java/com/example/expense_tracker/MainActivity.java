package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    List<Persoana> persaone;
    List<Student> studenti;

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

        persaone = new ArrayList<>();
        studenti = new ArrayList<>();
        Button signupButton = findViewById(R.id.button_signup);
        Button afiseazaLista = findViewById(R.id.button_login);
        Button signupStudent = findViewById(R.id.buttonStudent);
        Button listaStd = findViewById(R.id.buttonListaStud);
        Button listaImg = findViewById(R.id.buttonImg);


        listaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), AfiseazaListaImaginiActivity.class);
                startActivity(it);
            }
        });

        listaStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), AfiseazaStudentiActivity.class);
                it.putParcelableArrayListExtra("studenti", (ArrayList<? extends Parcelable>) studenti);
                startActivity(it);

            }
        });

        signupStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), MainActivity3.class);
                startActivityForResult(it, 2434);
            }
        });

        afiseazaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), AfiseazaPersoaneActivity.class);
                it.putParcelableArrayListExtra("lista", (ArrayList<? extends Parcelable>) persaone);
                startActivity(it);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), MainActivity2.class);
                startActivityForResult(it,111);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == -1){
            Persoana p = data.getParcelableExtra("persoana");
            persaone.add(p);
            Toast.makeText(getApplicationContext(), p.toString(), Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == 2434 && resultCode == -1){
            Student s = data.getParcelableExtra("student");
            studenti.add(s);
            Toast.makeText(this, s.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activitate","Pusa on Resume devine iar activa");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Activitate","Pusa On Start() afiseaza activitatea pe ecran");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Activitate","Pusa in Pause, e visibila dar inactiva");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Activitate","Pusa in stop nu mai e vizibila");
    }

}