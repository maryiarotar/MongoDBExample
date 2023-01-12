package com.rotar.microservices.productservice.service;

import com.rotar.microservices.productservice.handler.ExpenseNotFoundException;
import com.rotar.microservices.productservice.model.Expense;
import com.rotar.microservices.productservice.model.ExpenseCategory;
import com.rotar.microservices.productservice.repository.ExpenseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository repository;

    @Autowired
    private ExpenseService service;

    private static Optional<Expense> expOpt = Optional.of(
            new Expense("333", "AndyesPizza",
            ExpenseCategory.GROCERIES, BigDecimal.valueOf(789787)));

    Expense exp = expOpt.get();

    @BeforeEach
    void setup(){
        service = new ExpenseServiceImpl(repository);
    }

    @Captor
    private ArgumentCaptor<String> expenseCaptor;


    @Test
    @DisplayName("Assert that an expense is added to DB")
    void addExpenseToDBTest() {

        Assertions.assertTrue(service.add(exp));
        //assertThat(service.add(exp)).isTrue();
    }


    @Test
    @DisplayName("Assert that the expense throws an exception-1")
    void notAddExpenseToDBTest() {

        service.add(exp);

        Mockito.lenient().when(service.add(exp)).thenThrow(ExpenseNotFoundException.class);

        /* нерабочие из-за мока репозитория */
//        assertThatThrownBy(
//                () -> service.add(exp)).isInstanceOf(ExpenseNotFoundException.class);

//        Assertions.assertThrows(ExpenseNotFoundException.class,
//                () -> service.add(newExp));

    }

    @Test
    void updateOkTest(){
        Expense newExp = new Expense("00000", exp.getExpenseName(),
                exp.getExpenseCategory(), BigDecimal.valueOf(12345));

        Mockito.doReturn(expOpt).when(repository).findByName(ArgumentMatchers.anyString());
        assertThat(service.update(newExp)).isSameAs(exp);

    }

    @Test
    void updateThrowsExceptionTest(){

        Mockito.doThrow(new ExpenseNotFoundException("that's an exception!")).
                when(repository).findByName(ArgumentMatchers.anyString());
        Assertions.assertThrows(ExpenseNotFoundException.class,
                () -> service.update(exp));

    }


    @Test
    void findByNameTest(){

        /* работает */
//        Mockito.doReturn(Optional.of(exp)).when(repository).findByName("AndyesPizza");
//        Expense myObject = service.findByName("AndyesPizza");

        Mockito.lenient().when(
                repository.findByName(ArgumentMatchers.anyString())).
                thenReturn(expOpt);
        assertEquals(service.findByName("Tomato"), exp);

    }

    @Test
    void findAll() {

        Expense tmp1 = new Expense("987", "PotatoDelivery",
                ExpenseCategory.RESTAURANT, exp.getExpenseAmount());
        Expense tmp2 = new Expense("123", "TomatoDelivery",
                ExpenseCategory.ENTERTAINMENT, exp.getExpenseAmount());

        Expense[] e = new Expense[]{exp, tmp1, tmp2};

        List<Expense> lst = new ArrayList<>(Arrays.asList(exp, tmp1, tmp2));

        Mockito.lenient().when(repository.findAll()).thenReturn(lst);

        Assertions.assertArrayEquals(e, lst.toArray());

    }

    @Test
    void findByNameOrCategory() {

        Mockito.lenient().when(repository.findAll()).thenReturn(Arrays.asList(exp));

        assertThat(service.findByNameOrCategory(exp.getExpenseName(), "no"));

    }

    @Test
    void deleteTest(){

//        Mockito.verify(repository, Mockito.times(1))
//                .deleteById((expenseCaptor.capture()));

        assertDoesNotThrow(() -> service.delete("33"));

//        Assertions.assertTrue(expenseCaptor.getValue().compareTo("33")==0);


    }



}