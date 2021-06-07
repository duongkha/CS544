package miu.edu.attendance.repository;


import miu.edu.attendance.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional (propagation = Propagation.MANDATORY)
public interface CourseRepository extends JpaRepository<Course,Long> {



}
