package com.example.seminar_sapt4_1098;

import android.graphics.Bitmap;

public class Imagini {
    private String text;
    private Bitmap imagine;
    private String link;

    public Imagini(String text, Bitmap imagine, String link) {
        this.text = text;
        this.imagine = imagine;
        this.link = link;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
