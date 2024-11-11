package com.example.practice2_test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;



public class ListaJucatoriActivity extends AppCompatActivity {

    List<Jucator> jucatori = null;
    private int idModificat = 0;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_jucatori);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        jucatori = it.getParcelableArrayListExtra("jucatori");

        ListView jucatoriLv = findViewById(R.id.jucatoriLv);
        adapter = new JucatorAdapter(getApplicationContext(),jucatori, R.layout.row);
        jucatoriLv.setAdapter(adapter);

        jucatoriLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), MainActivity2.class);
                it.putExtra("jucator", jucatori.get(i));
                idModificat = i;
                startActivityForResult(it, 222);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 222){
            Jucator j = data.getParcelableExtra("jucator");
            jucatori.set(this.idModificat, j);
            adapter.notifyDataSetChanged();
        }
    }
}