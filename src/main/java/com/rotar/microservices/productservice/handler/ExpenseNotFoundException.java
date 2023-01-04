package com.rotar.microservices.productservice.handler;

public class ExpenseNotFoundException extends RuntimeException {

    public ExpenseNotFoundException(String message){
        super(message);
    }


}
