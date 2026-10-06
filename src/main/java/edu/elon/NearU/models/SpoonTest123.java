package edu.elon.NearU.models;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class SpoonTest123 {
    public static void main(String[] args) {
        // Initialize the API context
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCBxEsba86SZbGG0XuFfiAbgHb4cxCHwUc")
                .build();

        try {
            // Perform forward geocoding
//            GeocodingResult[] results = GeocodingApi.geocode(context, "1600 Amphitheatre Parkway, Mountain View, CA")
//                    .await();
            LatLng location = new LatLng(37.4223878, -122.0841883);
            GeocodingResult[] results = GeocodingApi.reverseGeocode(context, location).await();
            for(GeocodingResult res : results) {
                System.out.println(res.formattedAddress);
            }
//            System.out.println(results);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up threads and HTTP resources when done
            context.shutdown();
        }
    }
}