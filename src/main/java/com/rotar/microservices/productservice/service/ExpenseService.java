package com.rotar.microservices.productservice.service;

import com.rotar.microservices.productservice.model.Expense;
import com.rotar.microservices.productservice.model.ExpenseCategory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ExpenseService {

    void add(Expense expense);

    Expense update(Expense expense);

    List<Expense> findAll();

    Expense findByName(String name);

    List<Expense> findByNameOrCategory(String name, String category);

    void delete(String id);


}
