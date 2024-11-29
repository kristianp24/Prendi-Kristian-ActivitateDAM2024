package com.example.seminar_sapt4_1098;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Masina> masini = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        masini = new ArrayList<>();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.adgMasina){
            Intent it = new Intent(getApplicationContext(), MainActivity2.class);
            startActivityForResult(it,103);
        }
        else if(item.getItemId() == R.id.listMasina){
            Intent it = new Intent(getApplicationContext(), ListaMasini.class);
            it.putParcelableArrayListExtra("masini", (ArrayList<? extends Parcelable>) masini);
            startActivity(it);
        }
        else if(item.getItemId() == R.id.pozemasini){
            Intent it = new Intent(getApplicationContext(), AfisareImaginiActivity.class);
            startActivity(it);
        }
        else if(item.getItemId() == R.id.weather){
            Intent it = new Intent(getApplicationContext(), AfisareAPIActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==103){
            if(resultCode==RESULT_OK)
            {
                Masina masina = data.getParcelableExtra("masina");
                masini.add(masina);
            }
        }
    }
}