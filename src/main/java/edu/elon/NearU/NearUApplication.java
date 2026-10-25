package edu.elon.NearU;

import edu.elon.NearU.models.Location;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NearUApplication {

	public static void main(String[] args) {
		Location loc = new Location(37.419857, -122.078827);
		System.out.println(loc.getAddress());
		SpringApplication.run(NearUApplication.class, args);
	}



}
