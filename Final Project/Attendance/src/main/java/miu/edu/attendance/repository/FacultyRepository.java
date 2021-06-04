package miu.edu.attendance.repository;

import miu.edu.attendance.domain.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty,Long> {
    public List<Faculty> findAll();

    public Faculty getFacultyById(Long id);

    public Faculty getFacultyByUserId(@Param("userId") Long id);
}
