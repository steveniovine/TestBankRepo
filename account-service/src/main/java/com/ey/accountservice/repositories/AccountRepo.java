package com.ey.accountservice.repositories;

import com.ey.accountservice.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {







}
