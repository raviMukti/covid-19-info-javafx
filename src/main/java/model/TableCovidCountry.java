package model;

import lombok.Data;

@Data
public class TableCovidCountry {
    private String rangeByCountry;
    private String confirmedByCountry;
    private String recoveredByCountry;
    private String deathsByCountry;
}
