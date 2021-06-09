package miu.edu.attendance.service;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.dto.BarcodeRecordDTO;
import miu.edu.attendance.repository.BarcodeRecordRepository;
import miu.edu.attendance.repository.LocationRepository;
import miu.edu.attendance.repository.StudentRepository;
import miu.edu.attendance.repository.TimeSlotRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BarcodeRecordServiceImpl implements BarcodeRecordService{
	
	@Autowired
	BarcodeRecordRepository barcodeRecordRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	
	@Autowired
	TimeSlotRepository timeslotRepository;
	
	@Autowired
	LocationRepository locationRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public boolean add(BarcodeRecordDTO barcodeDTO) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findStudentBybarcodeId(barcodeDTO.getBarcodeId());
		if(student != null) {
			BarcodeRecord br = new BarcodeRecord();
			br.setBarcodeId(barcodeDTO.getBarcodeId());
			br.setDate(barcodeDTO.getDate());
	        		
			Optional<TimeSlot> ts = timeslotRepository.findAll().stream().filter(x->(x.getStartTime().compareTo(barcodeDTO.getDate().toLocalTime()) <= 0 && 
									x.getEndTime().compareTo(barcodeDTO.getDate().toLocalTime()) >= 0) || 
						(x.getStartTime().compareTo(barcodeDTO.getDate().toLocalTime()) > 0)).findFirst();
			if(ts.isPresent())
				br.setTimeSlot(ts.get());
			Optional<Location> lc = locationRepository.findById(barcodeDTO.getLocationId());
			if(lc.isPresent())
				br.setLocation(lc.get());
			br.setStudent(student);
			barcodeRecordRepository.save(br);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean update(BarcodeRecordDTO barcodeDTO) {
		
		return false;
	}

	@Override
	public boolean delete(Long id) {
		BarcodeRecord br = barcodeRecordRepository.findBarcodeRecordById(id);
		if(br != null) {
			barcodeRecordRepository.deleteById(id);
			return true;
		}
		return false;
	}


	@Override
	public BarcodeRecord findBarcodeRecordByID(Long id) {
		return barcodeRecordRepository.findBarcodeRecordById(id);
	}



	@Transactional(readOnly = true)
	@Override
	public List<BarcodeRecord> getAllBarcodeRecord() {
		return barcodeRecordRepository.findAll();
	}

	@Override
	/*
	 * get all attendance (barcode record) for a student on a course
	 */
	public List<AttendanceRecordDTO> findAllAttendanceByStudentIdAndCourse(Long studentId, Long courseId) {
		return barcodeRecordRepository.findAll().stream()
				.filter(x->x.getStudent().getId() == studentId && 
						x.getLocation().getSession().getCourseOffering().getCourse().getId() == courseId)
				.map(x->new AttendanceRecordDTO(x.getBarcodeId(),x.getDate(),x.getTimeSlot()!=null))
				.collect(Collectors.toList());
	}
    
}
