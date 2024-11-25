package com.example.seminar_sapt4_1098;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private List<Imagini> imagini;
    private Context context;
    private int resursaLayout;

    public ImageAdapter(List<Imagini> imagini, Context context, int res) {
        this.imagini = imagini;
        this.context = context;
        this.resursaLayout = res;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int i) {
        return imagini.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(resursaLayout,viewGroup,false);

        ImageView img = v.findViewById(R.id.imageView);
        TextView txt = v.findViewById(R.id.imageTxt);

        Imagini imagine = (Imagini) getItem(i);

        img.setImageBitmap(imagine.getImagine());
        txt.setText(imagine.getText());



        return v;
    }
}
