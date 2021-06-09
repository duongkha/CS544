package miu.edu.attendance.service;

import java.util.List;

import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.dto.FacultyDTO;

public interface FacultyService {
    List<Faculty> findAll();
    Faculty getFacultyByID(Long id);
    Faculty saveFaculty(Faculty faculty);
    Faculty updateFaculty(FacultyDTO facultyDto);
    Faculty deleteFaculty(Long id);
}
