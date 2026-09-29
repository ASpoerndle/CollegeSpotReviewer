package edu.elon.NearU.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Spot {
    private String spotId;
    private String spotName;
    public Spot() {

    }
    public Spot(String spotName) {
        this.spotName = spotName;

    }
    public void setSpotName(String spotName) {this.spotName = spotName;}
}
