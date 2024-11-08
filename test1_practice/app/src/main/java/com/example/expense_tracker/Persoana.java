package com.example.expense_tracker;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.util.Date;

public class Persoana implements Parcelable {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;

    public Persoana(String firstName, String lastName, Date birthDate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }

    public Persoana() {
    }

    // deserializare
    protected Persoana(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        this.birthDate =  new Date(in.readLong());
    }

    public static final Creator<Persoana> CREATOR = new Creator<Persoana>() {
        @Override
        public Persoana createFromParcel(Parcel in) {
            return new Persoana(in);
        }

        @Override
        public Persoana[] newArray(int size) {
            return new Persoana[size];
        }
    };

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Persoana{");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    //serializare
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(email);
        parcel.writeLong(this.birthDate != null ? this.birthDate.getTime() : -1);
        //getTime() returns the number of milliseconds since 1970, Date can not be parced
    }
}
