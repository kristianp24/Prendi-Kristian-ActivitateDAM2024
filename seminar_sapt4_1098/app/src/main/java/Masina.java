public class Masina {
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
