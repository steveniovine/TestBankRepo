package com.ey.accountservice.controllers;

import com.ey.accountservice.models.Transactions;
import com.ey.accountservice.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bankaccounts/transactions")
public class TransactionController {

    @Autowired
    TransactionsService ts;

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransactionsById(@PathVariable int id) {
        return ResponseEntity.ok(ts.getTransactions(id));
    }

    @GetMapping
    public ResponseEntity<List<Transactions>> getAllTransactions(){
        return ResponseEntity.ok(ts.getAllTransactions());
    }

/////Automatically adds transactions as they are done so we do not need to manually add them
//    @PostMapping
//    public ResponseEntity<Transactions> addTransactions(Transactions t) {
//        Transactions newTransactions = new Transactions();
//
//        return ResponseEntity.ok(ts.addTransactions(newTransactions));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transactions> deleteTransactions(@PathVariable int id){
        return ResponseEntity.ok(ts.deleteTransactions(id));
    }

    }

