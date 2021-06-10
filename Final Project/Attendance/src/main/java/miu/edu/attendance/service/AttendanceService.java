package miu.edu.attendance.service;

import java.util.List;

import miu.edu.attendance.dto.AttendanceRecordDTO;

public interface AttendanceService {
    public List<AttendanceRecordDTO> findAllAttendanceByStudentIdAndCourse(Long studentId, Long courseId, int pageNo, int size);
}
