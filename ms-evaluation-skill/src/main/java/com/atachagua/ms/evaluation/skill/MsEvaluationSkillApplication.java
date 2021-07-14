package com.atachagua.ms.evaluation.skill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsEvaluationSkillApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEvaluationSkillApplication.class, args);
	}

}
