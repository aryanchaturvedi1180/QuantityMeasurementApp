package com.example.authService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure()
				.directory("./")
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.load();

		setIfNotNull("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
		setIfNotNull("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));
		setIfNotNull("GOOGLE_SCOPE", dotenv.get("GOOGLE_SCOPE"));

		SpringApplication.run(AuthServiceApplication.class, args);
	}

	private static void setIfNotNull(String key, String value) {
		if (value != null && !value.isEmpty()) {
			System.setProperty(key, value);
		} else {
			System.out.println("⚠ Missing env: " + key);
		}
	}
}