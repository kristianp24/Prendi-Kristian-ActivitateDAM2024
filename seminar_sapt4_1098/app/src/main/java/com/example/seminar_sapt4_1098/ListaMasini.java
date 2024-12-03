package com.example.seminar_sapt4_1098;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaMasini extends AppCompatActivity {

    private List<Masina> masini = null;
    private int idModificat = 0;
    private MasinaAdapter adapter = null;
    private MyDatabase database;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_masini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//        Intent it = getIntent();
//        List<Masina> masini = it.getParcelableArrayListExtra("masini");
        masini = new ArrayList<>();
        database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "Masina").build();

        lv = findViewById(R.id.masiniLV);

        // android.R.layout.simple_list_item_1, layout pt o liste simpla cu un singur text
        // ArrayAdapter<Masina> adatper = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, masini);


        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                masini = database.getInterface().getMasini();

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new MasinaAdapter(masini,getApplicationContext(), R.layout.row_item);
                        lv.setAdapter(adapter);
                    }
                });
            }


        });

        //adapterul transpune informatiile in format vizual

        lv.setFocusable(false);
         lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intentModificat = new Intent(getApplicationContext(), MainActivity2.class);
               intentModificat.putExtra("masina", masini.get(i));
               idModificat = i;
               startActivityForResult(intentModificat, 244);
               Toast.makeText(getApplicationContext(), masini.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                database.getInterface().stergeMasina(masini.get(i));
                adapter.notifyDataSetChanged();// sa notifice ca datele s au schimbat
                return false;
            }
        });



    }
}