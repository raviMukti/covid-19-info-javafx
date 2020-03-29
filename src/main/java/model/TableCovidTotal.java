package model;

import lombok.Data;

@Data
public class TableCovidTotal {
    private String rangeWorld;
    private String confirmed;
    private String recovered;
    private String deaths;
}
