package com.example.seminar_sapt4_1098;

import android.os.Parcel;
import android.os.Parcelable;

public class Masina implements Parcelable {
    private String marca;
    private int anProducere;
    private float maxSpeed;
    private String tipConsumabil;
    private boolean esteElectrica;

    public Masina(String marca, int anProducere, float maxSpeed, String tipConsumabil, boolean esteElectrica) {
        this.marca = marca;
        this.anProducere = anProducere;
        this.maxSpeed = maxSpeed;
        this.tipConsumabil = tipConsumabil;
        this.esteElectrica = esteElectrica;
    }

    public Masina() {
    }

    //metoda de deserializare
    protected Masina(Parcel in) {
        marca = in.readString();
        anProducere = in.readInt();
        maxSpeed = in.readFloat();
        tipConsumabil = in.readString();
        esteElectrica = in.readByte() != 0;
    }

    //metoda de serializare
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(marca);
        dest.writeInt(anProducere);
        dest.writeFloat(maxSpeed);
        dest.writeString(tipConsumabil);
        dest.writeByte((byte) (esteElectrica ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //vector de obiecte serializate
    public static final Creator<Masina> CREATOR = new Creator<Masina>() {
        @Override
        public Masina createFromParcel(Parcel in) {
            return new Masina(in);
        }

        @Override
        public Masina[] newArray(int size) {
            return new Masina[size];
        }
    };

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnProducere() {
        return anProducere;
    }

    public void setAnProducere(int anProducere) {
        this.anProducere = anProducere;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getTipConsumabil() {
        return tipConsumabil;
    }

    public void setTipConsumabil(String tipConsumabil) {
        this.tipConsumabil = tipConsumabil;
    }

    public boolean isEsteElectrica() {
        return esteElectrica;
    }

    public void setEsteElectrica(boolean esteElectrica) {
        this.esteElectrica = esteElectrica;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Masina{");
        sb.append("marca='").append(marca).append('\'');
        sb.append(", anProducere=").append(anProducere);
        sb.append(", maxSpeed=").append(maxSpeed);
        sb.append(", tipConsumabil='").append(tipConsumabil).append('\'');
        sb.append(", esteElectrica=").append(esteElectrica);
        sb.append('}');
        return sb.toString();
    }
}
