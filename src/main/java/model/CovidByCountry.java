package model;

import lombok.Data;

@Data
public class CovidByCountry {
    private String country;
    private int confirmed;
    private int recovered;
    private int deaths;
    private double longitude;
    private double latitude;
}
