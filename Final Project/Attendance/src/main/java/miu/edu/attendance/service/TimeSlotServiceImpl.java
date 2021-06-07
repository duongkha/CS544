package miu.edu.attendance.service;

import miu.edu.attendance.domain.Admin;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.domain.User;
import miu.edu.attendance.dto.TimeSlotDTO;
import miu.edu.attendance.repository.AdminRepository;
import miu.edu.attendance.repository.FacultyRepository;
import miu.edu.attendance.repository.StudentRepository;
import miu.edu.attendance.repository.TimeSlotRepository;
import miu.edu.attendance.repository.UserRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService{

	@Autowired
	TimeSlotRepository timeslotRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public boolean add(TimeSlotDTO timeslot) {
		TimeSlot ts = timeslotRepository.save(modelMapper.map(timeslot, TimeSlot.class));
		return ts != null;
	}

	@Override
	public boolean update(TimeSlotDTO timeslot) {
		TimeSlot ts = timeslotRepository.findTimeSlotById(timeslot.getId());
		if(ts != null) {
			ts.setCode(timeslot.getCode());
			ts.setEndTime(timeslot.getEndTime());
			ts.setStartTime(timeslot.getStartTime());
			timeslotRepository.save(ts);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		TimeSlot ts = timeslotRepository.findTimeSlotById(id);
		if(ts != null) {
			timeslotRepository.delete(ts);
			return true;
		}
		return false;
	}

	@Override
	public TimeSlot findTimeSlotByID(Long id) {
		return timeslotRepository.findTimeSlotById(id);
	}

	@Override
	public List<TimeSlot> getAllTimeSlot() {
		return timeslotRepository.findAll();
	}
  
    
}
