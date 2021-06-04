package miu.edu.attendance.service;

import miu.edu.attendance.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findStudentById(Long id);
}
