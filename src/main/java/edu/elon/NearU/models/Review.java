package edu.elon.NearU.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Review.java
 * @author Dylan Arone
 * @version 2026.10.25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private String userName;
    private String reviewTitle;
    private String reviewDescription; // Large text field
    private double rating;            // Range: 0.0 to 5.0
    private Collage collage;          // Custom class storing images

    /**
     * Custom setter to enforce validation rules for the rating field,
     * while allowing Lombok to automatically generate getters, setters,
     * toString, equals, and hashCode for all other fields.
     */
    public void setRating(double rating) {
        if (rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 0.0 and 5.0");
        }
        this.rating = rating;
    }
}
