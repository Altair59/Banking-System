package AtmApp.Model.Transaction;//package com.atm_new.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UndoTransaction")
public class UndoTransaction {

    @Id
    String undoId;
    Transaction transaction;
    boolean completed;

    public UndoTransaction(Transaction transaction) {
        this.transaction = transaction;
        this.completed = false;
    }

    public String getUndoId() {
        return undoId;
    }


    public Transaction getTransaction() {
        return transaction;
    }


    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}