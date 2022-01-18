package com.tactopus.appoinment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tactopus.appoinment.model.RoleModel;

@Repository
public interface RoleRepo  extends MongoRepository<RoleModel,Integer>{

}
