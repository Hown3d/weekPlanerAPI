package de.ludi.weekPlanerAPI;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeekPlanerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeekPlanerApiApplication.class, args);
	}

}
