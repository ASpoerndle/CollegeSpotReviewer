package edu.elon.NearU.models;

public class LocationAddress {
    private String city;
    private String state;
    private String zipcode;
    private String address;
    private String country;

    public void setCity(String city) {
        this.city = city;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getAddress() {
        return this.address;
    }
    public String getCity() {return this.city;}
    public String getState() {return this.state;}
    public String getZipcode(){return this.zipcode;}
    public String getCountry() {return this.country;}

}
