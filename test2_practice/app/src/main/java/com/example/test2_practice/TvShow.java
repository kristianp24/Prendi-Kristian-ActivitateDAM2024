package com.example.test2_practice;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;

public class TvShow implements Parcelable {
    private float score;
    private int id;
    private String name;
    private List<String> genres;
    private Date premiered;

    public TvShow(float score, int id, String name, List<String> genres, Date premiered) {
        this.score = score;
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.premiered = premiered;
    }

    protected TvShow(Parcel in) {
        score = in.readFloat();
        id = in.readInt();
        name = in.readString();
        genres = in.createStringArrayList();
        premiered = new Date(in.readLong());
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPremiered() {
        return premiered;
    }

    public void setPremiered(Date premiered) {
        this.premiered = premiered;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeFloat(score);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeStringList(genres);
        parcel.writeLong(premiered.getTime());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TvShow{");
        sb.append("score=").append(score);
        sb.append(", id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", genres=").append(genres);
        sb.append(", premiered=").append(premiered);
        sb.append('}');
        return sb.toString();
    }
}
