package Models;

import java.sql.Date;

/**
 * Created by adrja on 01.03.2016.
 */
public class WeatherData {
    private int id;
    private String station_name;
    private String station_position;
    private Date timestamp;
    private double temperature;
    private int pressure;
    private int humidity;

    public WeatherData(String station_name, String station_position, Date timestamp, double temperature, int pressure, int humidity) {
        this.station_name = station_name;
        this.station_position = station_position;
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_position() {
        return station_position;
    }

    public void setStation_position(String station_position) {
        this.station_position = station_position;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "id=" + id +
                ", station_name='" + station_name + '\'' +
                ", station_position='" + station_position + '\'' +
                ", timestamp=" + timestamp +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
