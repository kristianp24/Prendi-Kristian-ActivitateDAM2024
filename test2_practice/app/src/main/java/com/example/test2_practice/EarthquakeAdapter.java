package com.example.test2_practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class EarthquakeAdapter extends BaseAdapter {
    private Context ctx;
    private List<Earthquake> earthquakes;
    private int resursaLayout;

    public EarthquakeAdapter(Context ctx, List<Earthquake> earthquakes, int resursaLayout) {
        this.ctx = ctx;
        this.earthquakes = earthquakes;
        this.resursaLayout = resursaLayout;
    }

    public int getResursaLayout() {
        return resursaLayout;
    }

    public void setResursaLayout(int resursaLayout) {
        this.resursaLayout = resursaLayout;
    }

    public List<Earthquake> getEarthquakes() {
        return earthquakes;
    }

    public void setEarthquakes(List<Earthquake> earthquakes) {
        this.earthquakes = earthquakes;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }


    @Override
    public int getCount() {
        return earthquakes.size();
    }

    @Override
    public Object getItem(int i) {
        return earthquakes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =  LayoutInflater.from(this.ctx);
        view = inflater.inflate(this.resursaLayout, viewGroup, false);

        TextView idTxt = view.findViewById(R.id.idTxt);
        TextView magTxt = view.findViewById(R.id.magnTxt);
        TextView plaecTxt = view.findViewById(R.id.placeTxt);
        RadioButton tsunamiBtn = view.findViewById(R.id.tsunamiRadio);

        Earthquake earthquake = (Earthquake) getItem(i);

        idTxt.setText(earthquake.getId());
        magTxt.setText(String.valueOf(earthquake.getMagnitude()));
        plaecTxt.setText(earthquake.getPlace());
        if (earthquake.getTsunami() != 0)
            tsunamiBtn.setChecked(true);

        return view;
    }
}
