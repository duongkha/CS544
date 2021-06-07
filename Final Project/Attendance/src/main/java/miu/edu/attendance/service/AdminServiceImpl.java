package miu.edu.attendance.service;

import miu.edu.attendance.domain.Admin;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.domain.User;
import miu.edu.attendance.repository.AdminRepository;
import miu.edu.attendance.repository.FacultyRepository;
import miu.edu.attendance.repository.StudentRepository;
import miu.edu.attendance.repository.UserRepository;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
	FacultyRepository facultyRepository;
    
    @Autowired
	StudentRepository studentRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    AdminRepository adminRepository;

    @Override
    public Boolean approveUser(long id) {
    	User user = userRepository.findUserById(id);
    	System.out.println("id"+ id);
    	if(user != null) {
    		if(user.isStudent()) {
    			Student student = studentRepository.findStudentByUserId(id);
    	        if(student != null){
    	        	student.getUser().setEnabled(true);
    	        	student.setApproved(true);
    	        	studentRepository.save(student);
    	        }
    		}
    		
    		if(user.isFaculty()) {
    			Faculty faculty = facultyRepository.getFacultyByUserId(id);
    	        if(faculty != null){
    	        	faculty.getUser().setEnabled(true);
    	        	faculty.setApproved(true);
    	            facultyRepository.save(faculty);
    	        }
    		}
    		
    		if(user.isAdmin()) {
    			Admin admin = adminRepository.findAdminByUserId(id);
    	        if(admin != null){
    	        	admin.getUser().setEnabled(true);
    	        	adminRepository.save(admin);
    	        }
    		}
    		return true;
    	}
    	
        return false;
    }
    
}
