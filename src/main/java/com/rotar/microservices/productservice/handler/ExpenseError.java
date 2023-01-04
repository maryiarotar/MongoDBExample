package com.rotar.microservices.productservice.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ExpenseError {

    private int statusCode;
    private String message;

}
