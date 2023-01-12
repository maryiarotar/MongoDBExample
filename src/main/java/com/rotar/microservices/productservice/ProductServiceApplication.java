package com.rotar.microservices.productservice;

import com.rotar.microservices.productservice.service.ExpenseService;
import com.rotar.microservices.productservice.service.ExpenseServiceImpl;
import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.math.BigDecimal;

@SpringBootApplication
@EnableMongock
@EnableMongoRepositories
public class ProductServiceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = SpringApplication.run(ProductServiceApplication.class, args);
		ExpenseService reconInputService = ctx.getBean(ExpenseServiceImpl.class);

	}

}
