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
    private Location location;
    public Spot() {
        this.spotId = generateID();
    }
    public Spot(String spotName, double latitude, double longitude) {
        super();
        setSpotName(this.spotName);
        location = new Location(latitude,longitude);


    }
    public void setSpotName(String spotName) {this.spotName = spotName;}
    private String generateID() {
        return String.valueOf(1);
    }

}


