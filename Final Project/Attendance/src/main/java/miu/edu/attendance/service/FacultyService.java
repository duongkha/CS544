package miu.edu.attendance.service;

import java.util.List;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.dto.FacultyDTO;

public interface FacultyService {
    List<Faculty> findAll();
    List<FacultyDTO> getAllFaculty();
    FacultyDTO getFacultyByID(Long id);
    boolean updateFaculty(Faculty faculty);
	boolean deleteFaculty(Long id);
	boolean addFaculty(Faculty faculty);
}
