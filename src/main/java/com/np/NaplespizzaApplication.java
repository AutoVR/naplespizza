package com.np;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.np.entity"})
public class NaplespizzaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(NaplespizzaApplication.class);
	
	public static void main(String[] args) {
		try {
			SpringApplication.run(NaplespizzaApplication.class, args);
		} catch(Exception e){
			LOGGER.error("Unable to start the service");
		}
	}
}
