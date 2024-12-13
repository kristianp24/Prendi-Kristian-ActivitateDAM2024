package com.example.practice2_test1;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Jucator implements Parcelable {
    @PrimaryKey
    private int id;
    private String numeComplet;
    private String echipa;
    private int varsta;
    private String stagiuCariera;

    public Jucator(int id, String numeComplet, String echipa, int varsta, String stagiuCariera) {
        this.id = id;
        this.numeComplet = numeComplet;
        this.echipa = echipa;
        this.varsta = varsta;
        this.stagiuCariera = stagiuCariera;
    }

    // deserializare
    protected Jucator(Parcel in) {
        id = in.readInt();
        numeComplet = in.readString();
        echipa = in.readString();
        varsta = in.readInt();
        stagiuCariera = in.readString();
    }

    public static final Creator<Jucator> CREATOR = new Creator<Jucator>() {
        @Override
        public Jucator createFromParcel(Parcel in) {
            return new Jucator(in);
        }

        @Override
        public Jucator[] newArray(int size) {
            return new Jucator[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getStagiuCariera() {
        return stagiuCariera;
    }

    public void setStagiuCariera(String stagiuCariera) {
        this.stagiuCariera = stagiuCariera;
    }

    public String getNumeComplet() {
        return numeComplet;
    }

    public void setNumeComplet(String numeComplet) {
        this.numeComplet = numeComplet;
    }

    public String getEchipa() {
        return echipa;
    }

    public void setEchipa(String echipa) {
        this.echipa = echipa;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Jucator{");
        sb.append("id=").append(id);
        sb.append(", numeComplet='").append(numeComplet).append('\'');
        sb.append(", echipa='").append(echipa).append('\'');
        sb.append(", varsta=").append(varsta);
        sb.append(", stagiuCariera='").append(stagiuCariera).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // serializare
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(numeComplet);
        parcel.writeString(echipa);
        parcel.writeInt(varsta);
        parcel.writeString(stagiuCariera);
    }
}
