package com.example.seminar_sapt4_1098;

public class DayForecast {
    private String Date;
    private String tempMin;
    private String tempMax;

    public DayForecast(String date, String tempMin, String tempMax) {
        Date = date;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Date='").append(Date).append('\'');
        sb.append(", tempMin='").append(tempMin).append('\'');
        sb.append(", tempMax='").append(tempMax).append('\'');
        sb.append('\n');
        return sb.toString();
    }
}
