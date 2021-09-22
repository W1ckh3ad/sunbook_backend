package de.sunbook.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
############ SunBook ###########
# Project Team: Marvin Leine,  #
# Patrick Töws, Jannic Decker, #
# Carolin Braun, Cristopher    #
# Gdynia, Lukas Reichelt       #
#                              #
#          24.09.2021          #
################################
*/

//This is the Backend of the SunBook Program 
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
