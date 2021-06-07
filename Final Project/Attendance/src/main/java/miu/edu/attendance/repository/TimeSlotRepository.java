package miu.edu.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.Admin;
import miu.edu.attendance.domain.TimeSlot;

import java.util.List;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TimeSlotRepository extends CrudRepository<TimeSlot,Long> {
    public List<TimeSlot> findAll();
    public TimeSlot findTimeSlotById(Long id);
}
