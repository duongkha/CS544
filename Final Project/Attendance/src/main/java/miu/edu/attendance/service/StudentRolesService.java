package miu.edu.attendance.service;

import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.dto.CourseOfferingDTO;
import miu.edu.attendance.dto.StudentRegistrationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentRolesService {

    public boolean register(StudentRegistrationDTO studentRegistrationDTO);
    public List<AttendanceRecordDTO> getAttendanceRecordForCourse(Long studentid, Long courseofferingid);
    public List<CourseOfferingDTO> getAllCourseOfferings(Long studentid);
}
