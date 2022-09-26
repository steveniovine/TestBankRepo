package com.ey.accountservice.services;

import com.ey.accountservice.models.Transactions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionsService {

    public Transactions addTransactions(Transactions t);

    public Transactions getTransactions(int id);

    public Transactions updateTransactions(Transactions change);

    public Transactions deleteTransactions(int id);

    List<Transactions> getAllTransactions();
}


