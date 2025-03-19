package com.example.spring_validation_fruits_vegetables.repositories;

import com.example.spring_validation_fruits_vegetables.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {
    public User getUserByUsername(@Param("username") String username);
}
