package com.example.seminar_sapt4_1098;

import android.app.Activity;
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

        Intent it = getIntent();
        if(it.hasExtra("masina")){
            Masina masina = it.getParcelableExtra("masina");
            marcaTxt.setText(masina.getMarca());
            vitezaTxt.setText(String.valueOf(masina.getMaxSpeed()));
            anProducereTxt.setText(String.valueOf(masina.getAnProducere()));
            tipCombustibil.setSelection(2);
            if(masina.isEsteElectrica())
                electrica.check(R.id.radio_Da);
            else
                electrica.check(R.id.radio_Nu);

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //aici o sa iei datele si sa le dai la prima activitate
                String marca = marcaTxt.getText().toString();
                float viteza = Float.parseFloat(vitezaTxt.getText().toString());
                int anProducere= Integer.parseInt(anProducereTxt.getText().toString());
                String tip_C = tipCombustibil.getSelectedItem().toString();
                boolean tipulMasiniiElectrica = false;;
               if (electrica.getCheckedRadioButtonId() == R.id.radio_Da){
                   tipulMasiniiElectrica = true;
               }

                // cu intent
                Masina masina = new Masina(marca,anProducere,viteza,tip_C,tipulMasiniiElectrica);
                Intent it = new Intent();
                it.putExtra("masina",masina);
                setResult(RESULT_OK,it);
                finish();
            }
        });
    }
}