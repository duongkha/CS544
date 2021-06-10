package miu.edu.attendance.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.dto.FacultyDTO;
import miu.edu.attendance.service.FacultyService;

@RestController
@RequestMapping(path = "/faculty", 
consumes = MediaType.APPLICATION_JSON_VALUE, 
produces = MediaType.APPLICATION_JSON_VALUE)
public class FacultyController {
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping(path = "/create")
	public FacultyDTO createCourseOffering(@RequestBody FacultyDTO facultyDto) {
		return modelMapper.map(facultyService.saveFaculty(modelMapper.map(facultyDto, Faculty.class)), FacultyDTO.class);
	}
	
	@GetMapping("/{id}")
	public FacultyDTO getFacultyById(@PathVariable Long id) {
		return modelMapper.map(facultyService.getFacultyByID(id), FacultyDTO.class);
	}
	
	@GetMapping("/all")
	public List<FacultyDTO> getAllFaculties(){
		List<Faculty> faculties = facultyService.findAll();
		return faculties.stream().map(x->modelMapper.map(x, FacultyDTO.class)).collect(Collectors.toList());
	}
	
	@PutMapping("/update")
	public FacultyDTO updateCourseOffering(@RequestBody FacultyDTO facultyDto) {
		return modelMapper.map(facultyService.updateFaculty(facultyDto), FacultyDTO.class);
	}
	
//	@GetMapping
//	public List<CourseOffering> getCourseOfferingsByFacultyId(@PathVariable Long facultyId){
//		
//	}
	
	@DeleteMapping("delete/{id}")
	public Faculty deleteCourseOffering(@PathVariable Long id) {
		return facultyService.deleteFaculty(id);
	}
	
}