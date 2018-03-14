package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;

	@PostConstruct
	public void init() {
		User user1 = new User("Pedro", "p1@hotmail.com");
		user1.setPassword("123123");
		User user2 = new User("Lucas", "p2@hotmail.com");
		user2.setPassword("123123");
		User user3 = new User("María", "p3@hotmail.com");
		user3.setPassword("123123");
		User user4 = new User("Marta", "p4@hotmail.com");
		user4.setPassword("123123");
		User user5 = new User("Pelayo", "p5@hotmail.com");
		user5.setPassword("123123");
		User user6 = new User("Edwuard", "p6@hotmail.com");
		user6.setPassword("123123");

		User user7 = new User("Willy", "p7@hotmail.com");
		user7.setPassword("123123");
		User user8 = new User("M", "p8@hotmail.com");
		user8.setPassword("123123");
		User user9 = new User("Álvaro", "p9@hotmail.com");
		user9.setPassword("123123");
		User user10 = new User("M.Khalifa", "p10@hotmail.com");
		user10.setPassword("123123");
		User user11 = new User("Darth Vader", "p11@hotmail.com");
		user11.setPassword("123123");
		User user12 = new User("De la cal", "p12@hotmail.com");
		user12.setPassword("123123");

		usersService.saveUser(user1);
		usersService.saveUser(user2);
		usersService.saveUser(user3);
		usersService.saveUser(user4);
		usersService.saveUser(user5);
		usersService.saveUser(user6);
		
		usersService.saveUser(user7);
		usersService.saveUser(user8);
		usersService.saveUser(user9);
		usersService.saveUser(user10);
		usersService.saveUser(user11);
		usersService.saveUser(user12);

	}

}
