package com.ey.accountservice.services;

import com.ey.accountservice.models.Account;
import com.ey.accountservice.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    //Test Comment
    public Account addAccount(Account a);
    public Account getAccount(int id);
    public Account updateAccount(Account change);
    public boolean deleteAccount(int id);

    List<Account> getAllAccounts();

    Account depositById(int id);
    Account withdrawalById(int id);

   List<Account> transfer(int fromThisBank, int toThisBank);

   User getUserByBankId(int id);


}
