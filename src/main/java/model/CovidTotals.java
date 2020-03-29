package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CovidTotals {
    private String confirmed;
    private String recovered;
    private String deaths;

}
