package weather_api;

import java.util.Date;

public class Pair {
    private String temp;
    private Date date;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pair(String temp) {
        this.temp = temp;
        this.date = new Date();
    }
}
