package io.fr0st.coronavirusmeter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusMeterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusMeterApplication.class, args);
	}

}
