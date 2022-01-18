package com.tactopus.appoinment.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tactopus.appoinment.beans.AppoinmentList;
import com.tactopus.appoinment.beans.BookSlot;
import com.tactopus.appoinment.beans.BookedDetls;
import com.tactopus.appoinment.beans.ConformBooking;
import com.tactopus.appoinment.beans.ConformResponse;
import com.tactopus.appoinment.beans.DocterList;
import com.tactopus.appoinment.beans.InfoList;
import com.tactopus.appoinment.beans.RoleList;
import com.tactopus.appoinment.beans.slots;
import com.tactopus.appoinment.model.BookSlotModel;
import com.tactopus.appoinment.model.ConformSlotModel;
import com.tactopus.appoinment.model.DocterModel;
import com.tactopus.appoinment.model.RoleModel;
import com.tactopus.appoinment.repository.BookSlotRepo;
import com.tactopus.appoinment.repository.ConformSlotRepo;
import com.tactopus.appoinment.repository.DocterRepo;
import com.tactopus.appoinment.repository.RoleRepo;

@Service
public class AppoinmentHandlerService {
	@Autowired
	private DocterRepo Docrepo;

	@Autowired
	private RoleRepo RoleRepo;

	@Autowired
	private BookSlotRepo BookSlotRepo;

	@Autowired
	private ConformSlotRepo ConformSlotRepo;

	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	private static final List<String> Time = Arrays.asList("09:00 - 10:00 IST", "10:00 - 11:00 IST",
			"11:00 - 12:00 IST", "12:00 - 01:00 IST", "01:00 - 02:00 IST", "02:00 - 03:00 IST", "03:00 - 04:00 IST",
			"04:00 - 05:00 IST");

	public List<ConformResponse> handleconformslot(List<ConformBooking> request) throws ParseException {

		List<ConformSlotModel> slotlist = new ArrayList<ConformSlotModel>();
		for (int i = 0; i < request.size(); i++) {

			ConformSlotModel slot = new ConformSlotModel();
			slot.setId(UUID.randomUUID().toString());
			slot.setDate(formatter.parse(request.get(i).getDate()));
			slot.setDoctor(request.get(i).getDoctor());
			slot.setName(request.get(i).getName());
			slot.setSpeciality(request.get(i).getSpeciality());
			slot.setSlot(request.get(i).getSlot());
			slot.setStatus("Scheduled");
			slotlist.add(slot);

		}
		ConformSlotRepo.saveAll(slotlist);
		return frameConformResponse(slotlist);
	}

	public List<BookedDetls> handleGetSlot(String Slot) throws ParseException {

		Date date;

		System.out.println(Slot);
		if (Slot == null)
			date = new Date();
		else
			date = formatter.parse(Slot);

		List<BookedDetls> bookedDetails = frameresponse(date);

		return bookedDetails;

	}

	public List<BookedDetls> handleBookSlot(BookSlot bookSlot) throws ParseException {

		BookSlotModel bs = new BookSlotModel();
		bs.setSlot(bookSlot.getSlot());
		bs.setDate(formatter.parse(bookSlot.getDate()));
		BookSlotRepo.save(bs);
		List<BookedDetls> bookedDetails = frameresponse(bs.getDate());
		return bookedDetails;

	}

	private List<BookedDetls> frameresponse(Date date) {

		List<Integer> booked = new ArrayList<Integer>();
		List<BookedDetls> bookedDetails = new ArrayList<BookedDetls>();
		List<ConformSlotModel> bookedSlots = ConformSlotRepo.findByDate(date);
		for (ConformSlotModel i : bookedSlots) {
			booked.add(i.getSlot());
		}
		for (int i = 0; i < Time.size(); i++) {
			BookedDetls bd = new BookedDetls();
			bd.setTime(Time.get(i));
			if (booked.contains(i)) {
				bd.setIsBooked(true);
			} else {
				bd.setIsBooked(false);
			}

			bookedDetails.add(bd);

		}
		return bookedDetails;
	}

	private List<ConformResponse> frameConformResponse(List<ConformSlotModel> request) throws ParseException {
		List<ConformResponse> conformationList = new ArrayList<ConformResponse>();
		Date date;
		for (ConformSlotModel detail : request) {
			ConformResponse conform = new ConformResponse();
			date = detail.getDate();
			String pattern = "EEE dd-MMM";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String dateformated = simpleDateFormat.format(date);
			dateformated = dateformated.replace("-", "th ");
			conform.setId(detail.getId());
			conform.setDate(dateformated);
			conform.setAttachment("Attachment");
			conform.setStatus(detail.getStatus());
			conform.setTime(Time.get(detail.getSlot()));
			conform.setTitle("Family Medicine consultant with " + detail.getDoctor());
			System.out.println(date);
			conformationList.add(conform);
		}
		return conformationList;
	}

	public List<AppoinmentList> handleGetAppoinment() throws ParseException {
		List<ConformSlotModel> appoinments = ConformSlotRepo.findAll();
		Map<String, List<ConformSlotModel>> map = new HashMap<String, List<ConformSlotModel>>();
		Collections.sort(appoinments, Collections.reverseOrder());
		String pattern = "dd-EEEE MMM";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		for (ConformSlotModel appoinment : appoinments) {
			String dateformated = simpleDateFormat.format(appoinment.getDate());
			dateformated = dateformated.replace("-", "th ");
			if (map.containsKey(dateformated)) {
				map.get(dateformated).add(appoinment);
			} else {
				List<ConformSlotModel> slot = new ArrayList<ConformSlotModel>();
				slot.add(appoinment);
				map.put(dateformated, slot);
			}

		}
		List<AppoinmentList> AppoinmentList = new ArrayList<AppoinmentList>();

		for (Map.Entry<String, List<ConformSlotModel>> entry : map.entrySet()) {

			AppoinmentList list = new AppoinmentList();
			String Date = entry.getKey() + " (" + entry.getValue().size() + ")";
			list.setDate(Date);
			list.setAppoinments(frameConformResponse(entry.getValue()));
			AppoinmentList.add(list);
			Collections.sort(AppoinmentList, Collections.reverseOrder());
		}
		return AppoinmentList;

	}

	public InfoList getInfohandler() {

		InfoList info = new InfoList();
		DocterList doclist = new DocterList();
		RoleList rolelist = new RoleList();
		List<String> docNameList = new ArrayList<String>();
		List<String> roleNameList = new ArrayList<String>();
		List<DocterModel> doc = Docrepo.findAll();
		List<RoleModel> role = RoleRepo.findAll();
		for (int i = 0; i < doc.size(); i++) {
			docNameList.add(doc.get(i).getName());
		}
		for (int i = 0; i < role.size(); i++) {
			roleNameList.add(role.get(i).getName());
		}
		doclist.setName(docNameList);
		rolelist.setRoleList(roleNameList);
		info.setDocters(doclist);
		info.setRoles(rolelist);
		return info;
	}

}
