package com.nikita.springwebexample8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;

@SpringBootApplication(exclude = ActiveMQAutoConfiguration.class)
public class SpringWebExample8Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebExample8Application.class, args);
	}

}
