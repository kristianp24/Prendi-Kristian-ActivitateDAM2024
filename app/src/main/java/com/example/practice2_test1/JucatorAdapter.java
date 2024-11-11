package com.example.practice2_test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class JucatorAdapter extends BaseAdapter {
    private Context context;
    private List<Jucator> jucatori;
    private int row_layout;

    public JucatorAdapter(Context context, List<Jucator> jucatori, int row_layout) {
        this.context = context;
        this.jucatori = jucatori;
        this.row_layout = row_layout;
    }

    @Override
    public int getCount() {
        return this.jucatori.size();
    }

    @Override
    public Object getItem(int i) {
        return this.jucatori.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(this.row_layout,viewGroup,false);

        TextView numeView = v.findViewById(R.id.afisareNumeTxt);
        TextView echipaView = v.findViewById(R.id.afisareEchipaTxt);
        TextView varstaView = v.findViewById(R.id.afisareVarstaTxt);
        TextView idView = v.findViewById(R.id.afisareIdTxt);
        TextView stadiulView = v.findViewById(R.id.afisareStadiultxt);

        Jucator j = (Jucator)getItem(i);
        numeView.setText(j.getNumeComplet());
        echipaView.setText(j.getEchipa());
        varstaView.setText(String.valueOf(j.getVarsta()));
        idView.setText(String.valueOf(j.getId()));
        stadiulView.setText(j.getStagiuCariera());


        return v;
    }
}
