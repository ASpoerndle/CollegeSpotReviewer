package edu.elon.NearU.models;

public class Location {
    private float latitude;
    private float longitude;
    private float[] coordinates;
    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        coordinates = new float[]{this.latitude, this.longitude};
    }

}
