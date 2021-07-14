package com.atachagua.ms.evaluation.experience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsEvaluationExperienceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEvaluationExperienceApplication.class, args);
	}

}
