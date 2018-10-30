package com.angelo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {
    User findById(String id);

    List <User> deleteById(String id);
}
