package com.planner.studyplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.planner")
public class StudyplannerApplication {

	public static void main(String[] args) {

		SpringApplication.run(StudyplannerApplication.class, args);

	}

}
