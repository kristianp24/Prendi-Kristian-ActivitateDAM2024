package com.example.expense_tracker;

import android.graphics.Bitmap;

public class Imagine {
    private Bitmap image;
    private String text;
    private String link;

    public Imagine(Bitmap image, String text, String link) {
        this.image = image;
        this.text = text;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
