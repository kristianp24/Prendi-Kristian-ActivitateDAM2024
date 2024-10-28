package com.example.seminar_sapt4_1098;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

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

        Button btn = findViewById(R.id.button_adauga);
        EditText marcaTxt = findViewById(R.id.marca_text);
        EditText vitezaTxt = findViewById(R.id.viteza_text);
        EditText anProducereTxt = findViewById(R.id.an_text);
        Spinner tipCombustibil = findViewById(R.id.action_bar_spinner);
        RadioGroup electrica  = findViewById(R.id.radioGrup);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               //aici o sa iei datele si sa le dai la prima activitate
                // cu intent
                Masina masina = new Masina("BMW",2020,200.5f,"benzina",false);
                Intent it = new Intent();
                it.putExtra("masina",masina);
                setResult(RESULT_OK,it);
                finish();;
            }
        });
    }
}