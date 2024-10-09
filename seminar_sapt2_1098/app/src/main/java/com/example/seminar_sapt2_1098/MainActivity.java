package com.example.seminar_sapt2_1098;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

        Log.wtf("Activitate","OnCreate()");
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.e("Activitate","S a apleat onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activitate","S a apelat onResume()");
        Toast.makeText(this,R.string.mesaj,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("Activitate","S a aplet onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Activitate", "S a apelat onStop()");
    }

    @Override
    protected void onDestroy() {
        Log.v("Activitate","S a apelat onDestroy()");//in momentul in care inchidem aplicatia de tot
        super.onDestroy();

    }
}