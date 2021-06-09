package miu.edu.attendance.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.Faculty;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty,Long> {
    
	public List<Faculty> findAll();

    public Faculty getFacultyById(Long id);

    public Faculty getFacultyByUserId(@Param("userId") Long id);
    
}
