package com.progetto.temperatureapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.progetto.temperatureapp.service.ServiceImplementation;

@SpringBootApplication
public class TemperatureAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemperatureAppApplication.class, args);
		
		ServiceImplementation service = new ServiceImplementation();
		service.saveEveryHour("Ancona");
		service.saveEveryHour("Milano");
		service.saveEveryHour("Bologna");
		service.saveEveryHour("Lecce");
		service.saveEveryHour("Cagliari");
		
		
	}

}
