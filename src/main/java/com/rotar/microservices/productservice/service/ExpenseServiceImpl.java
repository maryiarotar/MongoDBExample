package com.rotar.microservices.productservice.service;


import com.rotar.microservices.productservice.handler.ExpenseNotFoundException;
import com.rotar.microservices.productservice.model.Expense;
import com.rotar.microservices.productservice.model.ExpenseCategory;
import com.rotar.microservices.productservice.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    ExpenseServiceImpl(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void add(Expense expense) {

        expenseRepository.insert(expense);
    }

    @Override
    public Expense update(Expense expense) {

        Expense oldExpense = expenseRepository.findByName(expense.getExpenseName()).orElseThrow(
                () -> new ExpenseNotFoundException("Expense with name [" +
                        expense.getExpenseName() + "] not found!")
        );

        oldExpense.setExpenseName(expense.getExpenseName());
        oldExpense.setExpenseCategory(expense.getExpenseCategory());
        oldExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(oldExpense);
        return oldExpense;
    }

    @Override
    public List<Expense> findAll() {

        List<Expense> list = expenseRepository.findAll();
        return list;
    }

    @Override
    public Expense findByName(String name) {

        return expenseRepository.findByName(name).orElseThrow(
                () -> new ExpenseNotFoundException("Expense with name [" +
                        name + "] not found!")
        );

    }

    @Override
    public List<Expense> findByNameOrCategory(String name, String category){

        return expenseRepository.findNameOrCategory(name, category);
    }

    @Override
    public void delete(String id) {

        expenseRepository.deleteById(id);
    }
}
