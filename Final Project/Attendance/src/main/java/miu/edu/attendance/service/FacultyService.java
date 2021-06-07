package miu.edu.attendance.service;

import miu.edu.attendance.domain.Faculty;

import java.util.List;

public interface FacultyService {
    List<Faculty> findAll();
    Faculty getFacultyByID(Long id);
}
