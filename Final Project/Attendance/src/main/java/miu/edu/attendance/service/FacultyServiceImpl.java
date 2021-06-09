package miu.edu.attendance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//import com.sun.org.apache.xpath.internal.operations.Mod;
import miu.edu.attendance.dto.FacultyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.repository.FacultyRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService{
    @Autowired
    FacultyRepository facultyRepository;


	@Transactional(readOnly = true)
	@Override
	public List<Faculty> findAll() {


		return facultyRepository.findAll();
	}

	@Override
	public List<FacultyDTO> getAllFaculty() {
		List<Faculty> faculties= facultyRepository.findAll();
		List<FacultyDTO> facultyDTOS= faculties.stream().map(f->modelMapper.map(f,FacultyDTO.class)).collect(Collectors.toList());
        return facultyDTOS;
	}


	@Autowired
	ModelMapper modelMapper;
	@Override
	public boolean addFaculty(Faculty faculty) {
		if(facultyRepository.save(faculty)!=null){
			return true;
		}
		return false;
	}

    @Override
    public FacultyDTO getFacultyByID(Long id) {

		Faculty faculty= facultyRepository.findById(id).orElse(null);
		FacultyDTO facultyDTO=null;
		if(faculty!=null){
         facultyDTO=modelMapper.map(faculty,FacultyDTO.class);
       return facultyDTO;
		}
		return facultyDTO;
    }
    
    public boolean updateFaculty(Faculty faculty) {
		Faculty facultyToUpdate = facultyRepository.findById(faculty.getId()).orElse(null);
		if(facultyToUpdate!=null) {
			if (faculty.getDepartment() != null) {
				facultyToUpdate.setDepartment(faculty.getDepartment());
			}
			if (faculty.getTitle() != null) {
				facultyToUpdate.setTitle(faculty.getTitle());
			}

			if (faculty.getUser() != null) {
				facultyToUpdate.setUser(faculty.getUser());
			}
			return true;
		}

		return false;
	}

 @Override
	public boolean deleteFaculty(Long id) {
		Faculty deletedFaculty = facultyRepository.findById(id).orElse(null);
		if(deletedFaculty!=null) {
			facultyRepository.deleteById(id);
			return true;
		}
		return false;
	}




}
