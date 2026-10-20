package edu.elon.NearU.models;
import java.net.URI;
import java.sql.SQLOutput;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;


public class Location {
    private double latitude;
    private double longitude;
    private LocationAddress locationAddress = new LocationAddress();

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        locateAddressFromLatLong(this.latitude,this.longitude);
    }
    public String getAddress() {
        return this.locationAddress.getAddress();
    }
    public String getCity() {return this.locationAddress.getCity();}
    public String getState() {return this.locationAddress.getState();}
    public String getZipcode(){return this.locationAddress.getZipcode();}
    public String getCountry() {return this.locationAddress.getCountry();}

    public String[] locateAddressFromLatLong(double latitude, double longitude) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCBxEsba86SZbGG0XuFfiAbgHb4cxCHwUc")
                .build();

        try {

            LatLng location = new LatLng(latitude, longitude);
            GeocodingResult[] results = GeocodingApi.reverseGeocode(context, location).await();

            GeocodingResult firstResult = results[0];


            // 1. Set the full formatted address
            locationAddress.setAddress(firstResult.formattedAddress);

            // 2. Loop through address components to extract city, state, zip, and country
            if (firstResult.addressComponents != null) {
                for (AddressComponent component : firstResult.addressComponents) {
                    if (component.types != null) {
                        for (AddressComponentType type : component.types) {
                            switch (type) {
                                case LOCALITY:
                                    locationAddress.setCity(component.longName);
                                    break;
                                case ADMINISTRATIVE_AREA_LEVEL_1:
                                    locationAddress.setState(component.longName); // State
                                    break;
                                case POSTAL_CODE:
                                    locationAddress.setZipcode(component.longName);
                                    break;
                                case COUNTRY:
                                    locationAddress.setCountry(component.longName);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up threads and HTTP resources when done
            context.shutdown();
        }

    return null;
    }

}

