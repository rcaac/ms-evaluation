package com.atachagua.ms.evaluation.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsEvaluationProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEvaluationProfileApplication.class, args);
	}

}
