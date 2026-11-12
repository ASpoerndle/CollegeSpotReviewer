package edu.elon.NearU;

import edu.elon.NearU.entity.User;
import edu.elon.NearU.models.Location;
import edu.elon.NearU.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NearUApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;


	public static void main(String[] args) {
//		Location loc = new Location(37.419857, -122.078827);
//		System.out.println(loc.getAddress());
		SpringApplication.run(NearUApplication.class, args);
	}
	@Override
	public void run(String... args) {
		User firstUser = new User();
		firstUser.setName("Tom");

//		userRepository.save(firstUser);
//		userRepository.deleteById(1);
//		System.out.println(userRepository.findAll().getFirst().getName());
		System.out.println("User inserted successfully!");
	}



}
