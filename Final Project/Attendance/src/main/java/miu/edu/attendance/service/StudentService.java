package miu.edu.attendance.service;

import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.StudentDTOResponse;

import java.util.List;

public interface StudentService {
    public boolean addStudent (StudentDTOResponse student);
    public boolean updateStudent (StudentDTOResponse student);
    public boolean deleteStudentByID (Long id);
    public StudentDTOResponse findStudentByID( Long id);
    public List<StudentDTOResponse> getAllStudents();
    public List<Student> getAllStudentinfo();
}
