package com.ey.accountservice.repositories;

import com.ey.accountservice.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
}
