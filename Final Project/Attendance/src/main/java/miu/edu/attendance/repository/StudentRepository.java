package miu.edu.attendance.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.Student;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> findAll();
    Student findStudentById(Long id);
}
