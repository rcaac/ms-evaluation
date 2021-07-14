package com.atachagua.ms.evaluation.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsEvaluationTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEvaluationTrainingApplication.class, args);
	}

}
