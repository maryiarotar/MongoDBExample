package com.rotar.microservices.productservice.repository;

import com.rotar.microservices.productservice.model.Expense;
import com.rotar.microservices.productservice.model.ExpenseCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ExpenseRepository extends MongoRepository<Expense, String> {

    @Query("{'name' : ?0}")
    Optional<Expense> findByName(String name);

    @Query("{$or:[{'name' : ?0}, {'category' : ?1}]}")
    List<Expense> findNameOrCategory(String name, String category);

}
