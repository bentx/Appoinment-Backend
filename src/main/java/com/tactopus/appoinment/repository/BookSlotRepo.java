package com.tactopus.appoinment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tactopus.appoinment.beans.slots;
import com.tactopus.appoinment.model.BookSlotModel;
import com.tactopus.appoinment.model.DocterModel;

@Repository
public interface BookSlotRepo  extends MongoRepository<BookSlotModel,Integer> {

	
	
	public List<BookSlotModel>findByDate(Date date);
	
	 @Query(value="{ 'date' : ?0 }" ,fields="{ slot: 1, _id: 0 }")
	  List<slots> findByDateCustom(Date firstname);
}
