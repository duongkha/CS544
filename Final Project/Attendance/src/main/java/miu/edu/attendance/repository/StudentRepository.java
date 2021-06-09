package miu.edu.attendance.repository;


import miu.edu.attendance.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface StudentRepository extends CrudRepository<Student,Long> {
    List<Student> findAll();
    Student findStudentById(Long id);
    Student findStudentByUserId(Long id);
    Student findStudentBybarcodeId(String barcodeId);
}
