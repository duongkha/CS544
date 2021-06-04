package miu.edu.attendance.service;

import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FacultyServiceImpl implements FacultyService{
    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFacultyByID(Long id) {
        return facultyRepository.getFacultyById(id);
    }


}
