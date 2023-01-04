package com.rotar.microservices.productservice.controller;

import com.rotar.microservices.productservice.model.Expense;
import com.rotar.microservices.productservice.model.ExpenseCategory;
import com.rotar.microservices.productservice.service.ExpenseService;
import com.rotar.microservices.productservice.service.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my-example/mongodb")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping
    public ResponseEntity addExpense(@RequestBody Expense expense){

        expenseService.add(expense);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAll(){

        return ResponseEntity.ok(expenseService.findAll());

    }

    @PutMapping
    public ResponseEntity updateExpense(@RequestBody Expense expense){

        expenseService.update(expense);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExpense(@PathVariable String id){
        expenseService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{name}")
    public ResponseEntity<Expense> getByName(@PathVariable String name) {

            Expense exp = expenseService.findByName(name);
            return ResponseEntity.ok(exp);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Expense>> getByNameOrCategory(@RequestParam("name") String name,
                                       @RequestParam("category") String category) {

        List<Expense> exp = expenseService.findByNameOrCategory(name, category);
        return ResponseEntity.ok(exp);
    }


}
