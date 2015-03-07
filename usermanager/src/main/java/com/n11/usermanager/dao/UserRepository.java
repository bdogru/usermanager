package com.n11.usermanager.dao;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.n11.usermanager.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	Page<User> findAll(Pageable pageable);
	User findOne(String id);
}
