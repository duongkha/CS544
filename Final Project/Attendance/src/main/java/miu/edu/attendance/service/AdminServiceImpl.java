package miu.edu.attendance.service;

import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public Boolean approveFaculty(long id) {
    	Faculty faculty = facultyRepository.getFacultyById(id);
        if(faculty != null){
        	faculty.getUser().setEnabled(true);
        	faculty.setApproved(true);
            facultyRepository.save(faculty);
            return true;
        }
        return false;
    }
}
