package com.ey.accountservice.repositories;


import com.ey.accountservice.models.Transactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends CrudRepository<Transactions, Integer> {
}
