package io.fr0st.coronavirusmeter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoronavirusMeterApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoronavirusMeterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusMeterApplication.class, args);

		LOGGER.info("CoronavirusMeterApplication is up and Running now");
	}
}
