package com.example.back_asd.repositories;

import com.example.back_asd.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
