package miu.edu.attendance.service;

import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.repository.BarcodeRecordRepository;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService{
	
	@Autowired
	BarcodeRecordRepository barcodeRecordRepository;
	
	
	@Override
	/*
	 * get all attendance (barcode record) for a student on a course
	 */
	public List<AttendanceRecordDTO> findAllAttendanceByStudentIdAndCourse(Long studentId, Long courseId ,int pageNo, int size) {
		Pageable page = PageRequest.of(pageNo, size);
		return barcodeRecordRepository.findAll(page).stream()
				.filter(x->x.getStudent().getId() == studentId && 
						x.getLocation().getSession().getCourseOffering().getCourse().getId() == courseId) //paging
				.map(x->new AttendanceRecordDTO(x.getBarcodeId(),x.getDate(),x.getTimeSlot()!=null))
				.collect(Collectors.toList());
	}
    
}
