package miu.edu.attendance.controller;

import java.util.List;

import miu.edu.attendance.dto.FacultyDTO;
import miu.edu.attendance.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miu.edu.attendance.domain.Faculty;

@RestController
@RequestMapping(path = "faculties")
//consumes = MediaType.APPLICATION_JSON_VALUE,
//produces = MediaType.APPLICATION_JSON_VALUE)
public class FacultyController {
	
	@Autowired
	FacultyService facultyService;


	@GetMapping
	public List<FacultyDTO> getAllFaculties(){
		return facultyService.getAllFaculty();
	}

	@PostMapping(path = "/")
	public ResponseEntity addFaculty(@RequestBody Faculty faculty) {
		if(facultyService.addFaculty(faculty)){
			return  new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FacultyDTO> getFaculty(@PathVariable Long id) {

		FacultyDTO facultyDTO= facultyService.getFacultyByID(id);
		if(facultyDTO!=null){
			return new ResponseEntity<>(facultyDTO,HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);

	}

	@PutMapping("/")
	public ResponseEntity updateCourseOffering(@RequestBody Faculty faculty) {
		if(facultyService.updateFaculty(faculty)){
			return new ResponseEntity(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletefacultyById(@PathVariable Long id) {
		if(facultyService.deleteFaculty(id)){
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
}
