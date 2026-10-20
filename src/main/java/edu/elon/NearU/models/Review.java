package edu.elon.NearU.models;

public class Review {
    /*
    user name
    review name
    review desc
    rating
    Collage col

     */
    private String userName;
    private String reviewName;
    private String reviewDescription;
    private float rating;
    //collage class
    public Review(String userName, String reviewName, String reviewDescription, float rating) {
        setUserName(userName);
        setReviewName(reviewName);
        setReviewDescription(reviewDescription);
        setRating(rating);
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }
    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public String getUserName() {return this.userName;}
    public String getReviewName() {return this.reviewName;}
    public String getReviewDescription() {return this.reviewDescription;}
    public float getRating() {return this.rating;}

}
