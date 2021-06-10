package miu.edu.attendance.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.service.AttendanceService;

@CrossOrigin
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
	@Autowired
	AttendanceService attendanceService;
	  
	@GetMapping
    public List<AttendanceRecordDTO> getAttendanceReportByStudent(@RequestParam("studentid") Long studentId, 
		    		@RequestParam("courseid") Long courseId, 
		    		@RequestParam("page") int pageNo, @RequestParam("size") int size){
        List<AttendanceRecordDTO> attendances = attendanceService.findAllAttendanceByStudentIdAndCourse(studentId, courseId, pageNo, size) .stream()
        						.collect(Collectors.toList());
        return attendances;
	}
}
