package com.example.expense_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

        EditText lastNametxt = findViewById(R.id.lastnameTxt);
        EditText firstNametxt = findViewById(R.id.firstnameTxt);
        DatePicker bday = findViewById(R.id.bdayPicker);
        EditText emailTxt = findViewById(R.id.emailTxt);

        Button signUpButton = findViewById(R.id.button_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNametxt.getText().toString();
                String lastName = lastNametxt.getText().toString();

                int day = bday.getDayOfMonth();
                int month = bday.getMonth();
                int year = bday.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day);
                long millis = calendar.getTimeInMillis();
                Date bdayDate = new Date(millis);

                String email = emailTxt.getText().toString();
                Persoana p = new Persoana(firstName,lastName,bdayDate,email);

                Intent it = new Intent();
                it.putExtra("persoana",p);
                setResult(RESULT_OK, it);
                finish();
            }
        });

    }
}