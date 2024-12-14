package com.example.test2_practice;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class University implements Parcelable {
    private int id;
    private String name;
    private String code;
    private String webLink;

    public University(String name, String code, String webLink) {
        this.name = name;
        this.code = code;
        this.webLink = webLink;
    }

    protected University(Parcel in) {
        id = in.readInt();
        name = in.readString();
        code = in.readString();
        webLink = in.readString();
    }

    public static final Creator<University> CREATOR = new Creator<University>() {
        @Override
        public University createFromParcel(Parcel in) {
            return new University(in);
        }

        @Override
        public University[] newArray(int size) {
            return new University[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("University{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", webLink='").append(webLink).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(code);
        parcel.writeString(webLink);
    }
}
