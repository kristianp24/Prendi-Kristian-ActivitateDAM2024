package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText numeTxt = findViewById(R.id.fullNameTxt);
        EditText cnpTxt = findViewById(R.id.cnpTxt);
        EditText varstaTxt = findViewById(R.id.varstaTxt);
        EditText specializareTxt = findViewById(R.id.specializareTxt);
        RadioGroup esteUltAn = findViewById(R.id.radioUltAn);

        Button adaugareStud = findViewById(R.id.adaugareStudent);
        adaugareStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nume = numeTxt.getText().toString();
                String cnp = cnpTxt.getText().toString();
                int varsta = Integer.parseInt(varstaTxt.getText().toString());
                String specializare = specializareTxt.getText().toString();
                boolean ultAn = false;
                if (esteUltAn.getCheckedRadioButtonId() == R.id.radioDA)
                     ultAn = true;

                Student s = new Student(nume,varsta,cnp,specializare,ultAn);
                Intent it = new Intent();
                it.putExtra("student", s);
                setResult(-1, it);
                finish();

            }
        });
    }
}