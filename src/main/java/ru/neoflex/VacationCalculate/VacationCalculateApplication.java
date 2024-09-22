package ru.neoflex.VacationCalculate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.neoflex.VacationCalculate.service", "ru.neoflex.VacationCalculate.controller"})
public class VacationCalculateApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacationCalculateApplication.class, args);
	}

}
