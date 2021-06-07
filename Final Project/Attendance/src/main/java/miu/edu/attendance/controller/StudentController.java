package miu.edu.attendance.controller;

import miu.edu.attendance.dto.CourseDTO;
import miu.edu.attendance.dto.StudentDTOResponse;
import miu.edu.attendance.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    StudentService studentService;



    @GetMapping(value = "/")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return new ResponseEntity(studentService.getAllStudents(), HttpStatus.OK);
    }


    @RequestMapping(value={"/student"}, method= RequestMethod.POST)
    public void addStudent(@RequestBody StudentDTOResponse student){
        System.out.println("student saving");
        studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTOResponse> getStudent(@PathVariable int id){
       StudentDTOResponse studentDTOResponse= studentService.findStudentByID(Long.valueOf(id));
        if(studentDTOResponse!=null){

            return new ResponseEntity((studentDTOResponse), HttpStatus.OK);
        }
        return new ResponseEntity( HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value={"/{id}"}, method= RequestMethod.DELETE)
    public ResponseEntity deleteCourse(@PathVariable int id){
        System.out.println("course deleting");
        if(studentService.deleteStudentByID(Long.valueOf(id))){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
