package com.ey.accountservice.services;

import com.ey.accountservice.models.Account;
import com.ey.accountservice.models.User;
import com.ey.accountservice.repositories.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepo ar;

    @Override
    public Account addAccount(Account a) {
        return ar.save(a);
    }

    @Override
    public Account getAccount(int id){
        return ar.findById(id).get();
    }

    @Override
    public List<Account> getAllAccounts() {
        return (List<Account>) ar.findAll();
    }

    @Override
    public Account depositById(int id) {
        //Get the account
        Account ba =  ar.findById(id).get();
        ar.save(ba);
        return ba;
    }


    @Override
    public Account withdrawalById(int id) {
        //Get the account
        Account ba =  ar.findById(id).get();
        ar.save(ba);
        return ba;
    }

    @Override
    public List<Account> transfer(int fromThisBank, int toThisBank) {
        Account fromBank = ar.findById(fromThisBank).get();
        Account toBank = ar.findById(toThisBank).get();

        List<Account> accs = new ArrayList<>();
        accs.add(fromBank);
        accs.add(toBank);

        ar.save(fromBank);
        ar.save(toBank);

        return accs;

    }

    @Override
    public User getUserByBankId(int id) {
        Account account = ar.findById(id).get();
        User user = account.getUser_id();
        return user;
    }

    @Override
    public Account updateAccount(Account change) {
        return ar.save(change);


    }
    @Override
    public boolean deleteAccount(int id) throws EmptyResultDataAccessException {
        try {
            ar.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }

    }


}
