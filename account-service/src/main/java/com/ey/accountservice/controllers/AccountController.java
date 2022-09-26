package com.ey.accountservice.controllers;

import com.ey.accountservice.models.Account;
import com.ey.accountservice.models.Transactions;
import com.ey.accountservice.services.AccountService;
import com.ey.accountservice.services.TransactionsService;
import com.ey.accountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("bankaccounts")
public class AccountController {

    @Autowired
    AccountService as;

    @Autowired
    TransactionsService ts;

    @Autowired
    UserService us;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(as.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) {
        return ResponseEntity.ok(as.getAccount(id));
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<Account> depositById(@PathVariable int id,
                                                   @RequestParam int amount){

        try {
            //Get account
            Account account = as.getAccount(id);
            //Get the amount of specific account
            int curr_amount = account.getAmount();
            //Set the amount and incorporate the difference
            if ( amount <= account.getTransaction_limit()) {
                account.setAmount(curr_amount + amount);

                createAndSaveTransaction(id, amount);


                return ResponseEntity.ok(as.depositById(account.getId()));
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        } catch (Error e) {
            System.out.println("Cannot do that!");
            return null;
        }
    }

    @PutMapping("/{id}/withdrawal")
    public ResponseEntity<Account> withdrawalById(@PathVariable int id,
                                                      @RequestParam int amount){

        try {
            //Get account
            Account account = as.getAccount(id);

            //Get the amount of specific account
            int curr_amount = account.getAmount();

            if ( amount <= account.getTransaction_limit()) {
                //Set the amount and incorporate the difference
                account.setAmount(curr_amount - amount);
                createAndSaveTransaction(id, amount);
                return ResponseEntity.ok(as.withdrawalById(account.getId()));
            }
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        } catch(NullPointerException e) {
            System.out.println("Cannot do that!");
            return null;
        }
    }

    @PutMapping("transfer")
    public ResponseEntity<List<Account>> transfer(@RequestParam int from,
                                                      @RequestParam int to,
                                                      @RequestParam int amount) {

        Account fromBank = as.getAccount(from);
        Account toBank = as.getAccount(to);

        try {

            if (amount <= fromBank.getTransaction_limit()) {
                //Get the amount of specific account
                int curr_amount = fromBank.getAmount();

                //Subtract and set the amount
                fromBank.setAmount(curr_amount - amount);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }

            if (amount <= toBank.getTransaction_limit()) {
                //Get the amount of specific account
                int curr_amount2 = toBank.getAmount();

                //Add and set the amount
                toBank.setAmount(curr_amount2 + amount);

                createTransferTransaction(from, to, amount);

                return ResponseEntity.ok(as.transfer(from, to));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        } catch (Error e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/{id}/newAccount")
    public ResponseEntity<Account> addAccount(@PathVariable int id,
                              @RequestParam int amount,
                              @RequestParam String type,
                              @RequestParam String name,
                              @RequestParam int transactionLimit) {
        Account newAccount = new Account();
        newAccount.setUser_id(us.getUserById(id));
        newAccount.setAmount(amount);
        newAccount.setAcc_type(type);
        newAccount.setName(name);
        newAccount.setTransaction_limit(transactionLimit);

        return ResponseEntity.ok(as.addAccount(newAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable int id) {
        try {
            ResponseEntity.ok(as.deleteAccount(id));
            return ResponseEntity.ok(true);
        } catch (Error e) {
            return ResponseEntity.badRequest().build();
        }

    }

    private void createAndSaveTransaction(int id, int amount) {
        //Create Transaction
        Transactions newTransaction = new Transactions();

        //Get current time in millis
        long millis = System.currentTimeMillis();

        //Create and set start date
        Date newDate = new Date(millis);
        newTransaction.setStartDate(newDate.toString());

        //Set transaction properties
        newTransaction.setAmount(amount);
        newTransaction.setTargetAccountId(as.getUserByBankId(id));

        //Create and set completion date
        long milliFinish = System.currentTimeMillis();
        Date finishDate = new Date(milliFinish);
        newTransaction.setCompletionDate(finishDate.toString());
        ts.addTransactions(newTransaction);
    }

    private void createTransferTransaction(int from, int to, int amount) {
        //Create Transaction
        Transactions newTransaction = new Transactions();

        //Get current time in millis
        long millis = System.currentTimeMillis();

        //Create and set start date
        Date newDate = new Date(millis);
        newTransaction.setStartDate(newDate.toString());

        //Set transaction properties
        newTransaction.setAmount(amount);
        newTransaction.setUserAccountId(as.getUserByBankId(to));
        newTransaction.setTargetAccountId(as.getUserByBankId(from));

        //Create and set completion date
        long milliFinish = System.currentTimeMillis();
        Date finishDate = new Date(milliFinish);
        newTransaction.setCompletionDate(finishDate.toString());
        ts.addTransactions(newTransaction);
    }

}
