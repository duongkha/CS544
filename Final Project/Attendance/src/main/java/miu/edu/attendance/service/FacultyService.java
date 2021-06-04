package miu.edu.attendance.service;

import java.util.List;
import miu.edu.attendance.domain.Faculty;

public interface FacultyService {
    List<Faculty> findAll();
    Faculty getFacultyByID(Long id);
}
