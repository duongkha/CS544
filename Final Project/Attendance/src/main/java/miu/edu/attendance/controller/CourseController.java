package miu.edu.attendance.controller;


import miu.edu.attendance.domain.Course;
import miu.edu.attendance.dto.CourseDTO;
import miu.edu.attendance.service.CourseServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> getStudent(@PathVariable int id){


       Course course= courseService.findCourseByID(Long.valueOf(id));
       if(course!=null){
           System.out.println(course.getCourseName());
           return new ResponseEntity(modelMapper.map(course, CourseDTO.class), HttpStatus.OK);

       }
      return new ResponseEntity( HttpStatus.NOT_FOUND);

    }

    @RequestMapping(value={"/addCourse"}, method= RequestMethod.POST)
    public void addCourse(@RequestBody Course course){
        System.out.println("course saving");
        courseService.addCourse(course);
    }

    @RequestMapping(value={"/updateCourse"}, method= RequestMethod.PUT)
    public ResponseEntity updateCourse (@RequestBody Course course){
        System.out.println("course controller");
        if(courseService.updateCourse(course)){
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

    @RequestMapping(value={"/deleteCourse/{id}"}, method= RequestMethod.DELETE)
    public ResponseEntity deleteCourse(@PathVariable int id){
        System.out.println("course deleting");
       if(courseService.deleteCourseByID(Long.valueOf(id))){
           return new ResponseEntity<>(HttpStatus.ACCEPTED);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
