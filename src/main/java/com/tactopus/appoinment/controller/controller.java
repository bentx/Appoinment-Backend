package com.tactopus.appoinment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tactopus.appoinment.beans.AppoinmentList;
import com.tactopus.appoinment.beans.BookSlot;
import com.tactopus.appoinment.beans.BookedDetls;
import com.tactopus.appoinment.beans.ConformBooking;
import com.tactopus.appoinment.beans.ConformResponse;
import com.tactopus.appoinment.beans.DocterList;
import com.tactopus.appoinment.beans.InfoList;
import com.tactopus.appoinment.beans.RoleList;
import com.tactopus.appoinment.model.BookSlotModel;
import com.tactopus.appoinment.model.ConformSlotModel;
import com.tactopus.appoinment.model.DocterModel;
import com.tactopus.appoinment.model.RoleModel;
import com.tactopus.appoinment.repository.BookSlotRepo;
import com.tactopus.appoinment.repository.ConformSlotRepo;
import com.tactopus.appoinment.repository.DocterRepo;
import com.tactopus.appoinment.repository.RoleRepo;
import com.tactopus.appoinment.service.AppoinmentHandlerService;

@RestController
public class controller {

	@Autowired
	private DocterRepo Docrepo;

	@Autowired
	private RoleRepo RoleRepo;

	@Autowired
	private BookSlotRepo BookSlotRepo;

	@Autowired
	private AppoinmentHandlerService AppoinmentHandlerService;

	@Autowired
	private ConformSlotRepo ConformSlotRepo;

	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	@CrossOrigin
	@GetMapping("/getInfoList")
	InfoList getInfoList() {

		return AppoinmentHandlerService.getInfohandler();
	}

	@CrossOrigin
	@GetMapping("/getAppoinmentList")
	List<AppoinmentList> getAppoinment() throws ParseException {
		return AppoinmentHandlerService.handleGetAppoinment();

	}

	@CrossOrigin
	@GetMapping("/cancel/{id}")
	List<AppoinmentList> getAppoinment(@PathVariable String id) throws ParseException {
		Optional<ConformSlotModel> slotOption = ConformSlotRepo.findById(id);
		if (slotOption.isPresent()) {
			ConformSlotModel slot = slotOption.get();
			slot.setStatus("Cancelled");
			ConformSlotRepo.save(slot);
		}

		return AppoinmentHandlerService.handleGetAppoinment();

	}

	@CrossOrigin
	@PostMapping("/bookSlot")
	List<BookedDetls> bookSlot(@RequestBody BookSlot bookSlot) throws ParseException {
		return AppoinmentHandlerService.handleBookSlot(bookSlot);
	}

	@CrossOrigin
	@GetMapping("/getSlot/{slot}")
	List<BookedDetls> getSlot(@PathVariable(required = false) String slot) throws ParseException {
		return AppoinmentHandlerService.handleGetSlot(slot);
	}

	@PostMapping("/addRole")
	String addDoctor(@RequestBody RoleModel role) {
		RoleRepo.save(role);
		return "{status:success}";

	}

	@CrossOrigin
	@PostMapping("/conform")
	List<ConformResponse> conformBooking(@RequestBody List<ConformBooking> request) throws ParseException {

		return AppoinmentHandlerService.handleconformslot(request);

	}

}
