package miu.edu.attendance.service;

import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.dto.CourseOfferingDTO;

import java.util.List;

public interface FacultyRolesService {

    public boolean addCourseToFaculty(Long facultyId, Long courseofferingId);
    public List<AttendanceRecordDTO> findAllAttendanceByFacultyIdAndCourseOffering(Long studentId, Long courseId);
    public List<CourseOfferingDTO> findAllCourseOfferingsByFacultyId(Long facultyid);
}
