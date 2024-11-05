package com.example.seminar_sapt4_1098;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.List;

public class ListaMasini extends AppCompatActivity {

    private List<Masina> masini = null;
    private int idModificat = 0;
    private MasinaAdapter adapter = null;
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


        Intent it = getIntent();
        List<Masina> masini = it.getParcelableArrayListExtra("masini");

        //adapterul transpune informatiile in format vizual
        ListView lv = findViewById(R.id.masiniLV);

        // android.R.layout.simple_list_item_1, layout pt o liste simpla cu un singur text
       // ArrayAdapter<Masina> adatper = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, masini);
        this.adapter = new MasinaAdapter(masini,getApplicationContext(), R.layout.row_item);
        lv.setAdapter(this.adapter);
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

//        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                masini.remove(i);
//                adatper.notifyDataSetChanged();// sa notifice ca datele s au schimbat
//                return false;
//            }
//        });



    }
}