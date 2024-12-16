package com.example.test2_practice;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Earthquake implements Parcelable {
    @PrimaryKey
    @NonNull
    private String id;
    private String place;
    private double magnitude;
    private int tsunami;

    public Earthquake(String id, String place, double magnitude, int tsunami) {
        this.id = id;
        this.place = place;
        this.magnitude = magnitude;
        this.tsunami = tsunami;
    }

    protected Earthquake(Parcel in) {
        id = in.readString();
        place = in.readString();
        magnitude = in.readDouble();
        tsunami = in.readInt();
    }

    public static final Creator<Earthquake> CREATOR = new Creator<Earthquake>() {
        @Override
        public Earthquake createFromParcel(Parcel in) {
            return new Earthquake(in);
        }

        @Override
        public Earthquake[] newArray(int size) {
            return new Earthquake[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public int getTsunami() {
        return tsunami;
    }

    public void setTsunami(int tsunami) {
        this.tsunami = tsunami;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Earthquake{");
        sb.append("id='").append(id).append('\'');
        sb.append(", place='").append(place).append('\'');
        sb.append(", magnitude=").append(magnitude);
        sb.append(", tsunami=").append(tsunami);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(place);
        parcel.writeDouble(magnitude);
        parcel.writeInt(tsunami);
    }
}
