package edu.elon.NearU.models;

public class Location {
    private float latitude;
    private float longitude;
    private String city;
    private String state;
    private String zipcode;
    private String address;
    private String country;

    public Location(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = "";
        this.city = "";
        this.country = "";
        this.zipcode = "";
        this.state = "";

    }
    public String[] locateAddressFromLatLong(float latitude, float longitude) {
        //call an API to get address
        return null;
    }

}
