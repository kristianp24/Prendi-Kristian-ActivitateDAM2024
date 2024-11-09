package com.example.expense_tracker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Student implements Parcelable {
    private String numeComplet;
    private int varsta;
    private String CNP;
    private String Specializarea;
    private boolean ultimulAm;

    public Student() {
    }

    public Student(String numeComplet, int varsta, String CNP, String specializarea, boolean ultimulAm) {
        this.numeComplet = numeComplet;
        this.varsta = varsta;
        this.CNP = CNP;
        Specializarea = specializarea;
        this.ultimulAm = ultimulAm;
    }

    protected Student(Parcel in) {
        numeComplet = in.readString();
        varsta = in.readInt();
        CNP = in.readString();
        Specializarea = in.readString();
        ultimulAm = in.readByte() != 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getNumeComplet() {
        return numeComplet;
    }

    public void setNumeComplet(String numeComplet) {
        this.numeComplet = numeComplet;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getSpecializarea() {
        return Specializarea;
    }

    public void setSpecializarea(String specializarea) {
        Specializarea = specializarea;
    }

    public boolean isUltimulAm() {
        return ultimulAm;
    }

    public void setUltimulAm(boolean ultimulAm) {
        this.ultimulAm = ultimulAm;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("numeComplet='").append(numeComplet).append('\'');
        sb.append(", varsta=").append(varsta);
        sb.append(", CNP='").append(CNP).append('\'');
        sb.append(", Specializarea='").append(Specializarea).append('\'');
        sb.append(", ultimulAm=").append(ultimulAm);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(numeComplet);
        parcel.writeInt(varsta);
        parcel.writeString(CNP);
        parcel.writeString(Specializarea);
        parcel.writeByte((byte) (ultimulAm ? 1 : 0));
    }
}
