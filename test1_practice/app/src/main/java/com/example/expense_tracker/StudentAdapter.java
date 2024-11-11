package com.example.expense_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private List<Student> studenti;
    private Context context;
    private int layout_row;

    public StudentAdapter(List<Student> studenti, Context context, int layout_row) {
        this.studenti = studenti;
        this.context = context;
        this.layout_row = layout_row;
    }

    @Override
    public int getCount() {
        return studenti.size();
    }

    @Override
    public Object getItem(int i) {
        return studenti.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(this.layout_row, viewGroup, false);
        // creates a view from a xml layout fule (this.layout_row)

        TextView numeTxt = v.findViewById(R.id.afisareNumeTxt);
        TextView varstaTxt = v.findViewById(R.id.afisareVarstaTxt);
        TextView cnpTxt = v.findViewById(R.id.afisareCNPtxt);
        TextView specialziareTxt = v.findViewById(R.id.afisareSpecializareTxt);

        if (numeTxt == null || varstaTxt == null || cnpTxt == null || specialziareTxt == null) {
            throw new IllegalStateException("One or more TextView elements are missing in the layout XML.");
        }

        Student s= (Student)getItem(i);

        numeTxt.setText(s.getNumeComplet());
        varstaTxt.setText(String.valueOf(s.getVarsta()));
        cnpTxt.setText(s.getCNP());
        specialziareTxt.setText(s.getSpecializarea());

        return v;
    }
}
