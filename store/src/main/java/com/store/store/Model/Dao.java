package com.store.store.Model;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Dao extends MongoRepository<Emp,String> {

    Optional<Emp> findByEmail(String email);

    void deleteByEmail(String email);
	
}

