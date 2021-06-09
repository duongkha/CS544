package miu.edu.attendance.controller;


import miu.edu.attendance.domain.Course;
import miu.edu.attendance.dto.CourseDTO;
import miu.edu.attendance.service.CourseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(value = "/")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return new ResponseEntity(courseService.getAllCourses(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable int id){


       Course course= courseService.findCourseByID(Long.valueOf(id));
       if(course!=null){
           System.out.println(course.getCourseName());
           return new ResponseEntity(modelMapper.map(course, CourseDTO.class), HttpStatus.OK);

       }
      return new ResponseEntity( HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value={"/"}, method= RequestMethod.POST)
    public ResponseEntity addCourse( @Valid @RequestBody Course course, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity(result.getFieldError(),HttpStatus.BAD_REQUEST);
        }
        courseService.addCourse(course);
        return new ResponseEntity(HttpStatus.ACCEPTED);


    }

    @RequestMapping(value={"/"}, method= RequestMethod.PUT)
    public ResponseEntity updateCourse ( @RequestBody CourseDTO courseDTO  ){



        System.out.println("course controller");
        if(courseService.updateCourse(courseDTO)){
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

    @RequestMapping(value={"/{id}"}, method= RequestMethod.DELETE)
    public ResponseEntity deleteCourse(@PathVariable int id){
        System.out.println("course deleting");
       if(courseService.deleteCourseByID(Long.valueOf(id))){
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
