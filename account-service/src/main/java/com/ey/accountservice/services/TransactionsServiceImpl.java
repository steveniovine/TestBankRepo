package com.ey.accountservice.services;

import com.ey.accountservice.models.Transactions;
import com.ey.accountservice.repositories.TransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    TransactionsRepo transactionsRepo;


    @Override
    public List<Transactions> getAllTransactions() {
        return (List<Transactions>) transactionsRepo.findAll();
    }

    @Override
    public Transactions addTransactions(Transactions t) {
        return transactionsRepo.save(t);
    }

    @Override
    public Transactions getTransactions(int id) {
        return transactionsRepo.findById(id).get();
    }

    @Override
    public Transactions updateTransactions(Transactions change) {
        return transactionsRepo.save(change);
    }

    @Override
    public Transactions deleteTransactions(int id) {
        try{
            Transactions deletedTransaction = getTransactions(id);
            transactionsRepo.deleteById(id);
            return deletedTransaction;
        } catch (Error e) {
            e.printStackTrace();
            return null;
        }
    }


}




