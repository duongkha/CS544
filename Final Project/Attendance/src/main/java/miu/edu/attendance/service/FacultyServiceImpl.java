package miu.edu.attendance.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.dto.FacultyDTO;
import miu.edu.attendance.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService{
    
	@Autowired
	ModelMapper modelMapper;
	
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

	@Override
	public Faculty saveFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	@Override
	public Faculty updateFaculty(FacultyDTO facultyDto) {
		Faculty facultyToUpdate = facultyRepository.getFacultyById(facultyDto.getId());
		if(facultyToUpdate != null) {
			facultyToUpdate.setTitle(facultyDto.getTitle());
			facultyToUpdate.setDepartment(facultyDto.getDepartment());
			facultyToUpdate.setCourseOfferings(facultyDto.getCourseOfferings());
			facultyToUpdate.setApproved(facultyDto.isApproved());
			facultyToUpdate.setFirstName(facultyDto.getFirstName());
			facultyToUpdate.setLastName(facultyDto.getLastName());
			facultyRepository.save(facultyToUpdate);
			return facultyToUpdate;
		}
		return null;
	}

	@Override
	public Faculty deleteFaculty(Long id) {
		Faculty deletedFaculty = facultyRepository.getFacultyById(id);
		if(deletedFaculty != null) {
			facultyRepository.deleteById(id);
			return deletedFaculty;
		}
		return null;
	}


}
