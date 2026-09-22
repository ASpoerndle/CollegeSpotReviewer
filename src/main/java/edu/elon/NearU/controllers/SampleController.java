package edu.elon.NearU.controllers;

import edu.elon.NearU.models.Spot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("hello")
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping("getaspot")
    public Spot returnSpot() {
        Spot mySpot = new Spot();
        mySpot.setSpotName("First Spot");
        return mySpot;
    }
}
