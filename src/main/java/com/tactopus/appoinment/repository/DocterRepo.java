package com.tactopus.appoinment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tactopus.appoinment.model.DocterModel;



@Repository
public interface DocterRepo extends MongoRepository<DocterModel,Integer> {

}
