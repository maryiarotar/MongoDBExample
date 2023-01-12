package com.rotar.microservices.productservice;

import com.rotar.microservices.productservice.model.Expense;
import com.rotar.microservices.productservice.model.ExpenseCategory;
import com.rotar.microservices.productservice.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mongounit.AssertMatchesDataset;
import org.mongounit.MongoUnitTest;
import org.mongounit.SeedWithDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest(properties = {"spring.mongodb.embedded.version=5.1.3"})
@ExtendWith(SpringExtension.class)
@MongoUnitTest(name = "repository-test")
@ActiveProfiles("repository-test")
@EnableMongoRepositories(basePackages = "src.main.java.com.rotar.microservices.productservice.repository.ExpenseRepository")

public class ProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ExpenseRepository repository;

	@Test
	@AssertMatchesDataset
	@SeedWithDataset
	void savingTest(){
		Expense exp =  new Expense("111", "aaa",
				ExpenseCategory.RESTAURANT, BigDecimal.TEN);
		Expense savedExp = repository.save(exp);

		assertThat(savedExp).usingRecursiveComparison()
				.ignoringFields("id").isEqualTo(exp);
	}


}
