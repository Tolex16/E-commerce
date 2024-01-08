package com.example.ecommerce;

import com.example.ecommerce.Entity.Role;
import com.example.ecommerce.Entity.UserEntity;
import com.example.ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ECommerceApplication implements CommandLineRunner {

	@Autowired
	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserEntity adminAccount = userRepository.findByRole(Role.ADMIN);
		if (null == adminAccount){
			UserEntity user = new UserEntity();

			user.setFirstName("Tochukwu");
			user.setMiddleName("Emmanuel");
			user.setLastName("Nna-Edozie");
			user.setEmail("tolex20004real@gmail.com");
			user.setDateOfBirth(null);
			user.setPhoneNumber("09031640479");
			user.setRole(Role.ADMIN);
			user.setPassword(passwordEncoder.encode("admin"));
			user.setConfirmPassword(passwordEncoder.encode("admin"));

			userRepository.save(user);
		}
	}
}
