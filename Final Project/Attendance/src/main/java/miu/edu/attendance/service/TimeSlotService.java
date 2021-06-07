package miu.edu.attendance.service;

import java.util.List;

import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.TimeSlotDTO;


public interface TimeSlotService {
	public boolean add(TimeSlotDTO timeslot);
    public boolean update(TimeSlotDTO timeslot);
    public boolean delete(Long id);
    public TimeSlot findTimeSlotByID( Long id);
    public List<TimeSlot> getAllTimeSlot();
}
