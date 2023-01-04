package com.rotar.microservices.productservice.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.rotar.microservices.productservice.service.ExpenseService;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id="myMigrationChangeUnitId", order = "1", author = "mongock_test", systemVersion = "1")
public class DatabaseChangeLog {
    private final MongoTemplate template;

    @Autowired
    private ExpenseService expenseService;

    public DatabaseChangeLog(MongoTemplate template) {
        this.template = template;
    }
    //Note this method / annotation is Optional
    @BeforeExecution
    public void before() {
        template.createCollection("expences");
    }
    //Note this method / annotation is Optional
/*    @RollbackBeforeExecution
    public void rollbackBefore() {
        template.dropCollection("clients");
    }*/
    @Execution
    public void migrationMethod() {
        expenseService.findAll()
                .stream()
                .forEach(clientDocument -> template.save(clientDocument, "expense_COLLECTION_NAME"));
    }
/*    @RollbackExecution
    public void rollback() {
        mongoTemplate.deleteMany(new Document());
    }*/
}

