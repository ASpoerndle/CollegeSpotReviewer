package edu.elon.NearU.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
//@NoArgsConstructor
public class Spot {
    private String spotId;
    private String spotName;
    public Spot() {
        this.spotId = generateID();
    }
    public Spot(String spotName) {
        this.spotName = spotName;

    }
    public void setSpotName(String spotName) {this.spotName = spotName;}
    private String generateID() {
        return String.valueOf(1);
    }

}
