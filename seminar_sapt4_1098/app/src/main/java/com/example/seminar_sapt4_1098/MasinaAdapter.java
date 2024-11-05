package com.example.seminar_sapt4_1098;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class MasinaAdapter extends BaseAdapter {

    private List<Masina> masini = null;
    private Context ctx;
    private int resursaLayout;

    public MasinaAdapter(List<Masina> masini, Context ctx, int resursaLayout) {
        this.masini = masini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return masini.size();
    }

    @Override
    public Object getItem(int i) {
        return masini.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View v = inflater.inflate(resursaLayout,viewGroup,false);

        TextView marcaTxt = v.findViewById(R.id.viewMarca);
        TextView anProdTxt = v.findViewById(R.id.viewAnProducere);
        TextView combsTxt = v.findViewById(R.id.viewCombustabil);
        TextView maxSpeedTxt = v.findViewById(R.id.viewMaxSpeed);
        RadioButton rdElectrica = v.findViewById(R.id.viewElectrica);

        Masina masina = (Masina)getItem(i);

        marcaTxt.setText(masina.getMarca());
        anProdTxt.setText(""+masina.getAnProducere());
        combsTxt.setText(String.valueOf(masina.getTipConsumabil()));
        maxSpeedTxt.setText(String.valueOf(masina.getMaxSpeed()));
        if (masina.isEsteElectrica())
            rdElectrica.setChecked(true);


        return v;
    }
}
