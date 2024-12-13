package com.example.expense_tracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapterPoze extends BaseAdapter {
      private List<Imagine> imagini;
      private Context ctx;
      private int resursa;

    public CustomAdapterPoze(List<Imagine> imagini, Context ctx, int resursa) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursa = resursa;
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
        LayoutInflater inflater = LayoutInflater.from(this.ctx);
        View view1 = inflater.inflate(R.layout.imagini_row, viewGroup, false);

        Imagine im = this.imagini.get(i);

        ImageView iv = view1.findViewById(R.id.imgView);
        iv.setImageBitmap(im.getImage());
        TextView tv = view1.findViewById(R.id.txtView);
        tv.setText(im.getText());

        return view1;
    }
}
