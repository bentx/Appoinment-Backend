package com.tactopus.appoinment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tactopus.appoinment.model.BookSlotModel;
import com.tactopus.appoinment.model.ConformSlotModel;

public interface ConformSlotRepo extends MongoRepository<ConformSlotModel,String>  {
	
	public List<ConformSlotModel>findByDate(Date date);
}
