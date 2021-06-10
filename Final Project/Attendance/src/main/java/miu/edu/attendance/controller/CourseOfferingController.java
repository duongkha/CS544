package miu.edu.attendance.controller;

import miu.edu.attendance.dto.CourseOfferingDTO;
import miu.edu.attendance.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courseofferings")
public class CourseOfferingController {

@Autowired
CourseOfferingService courseOfferingService;


    @GetMapping(value = "/")
    public ResponseEntity<List<CourseOfferingDTO>> getAllCourseOfferings(){
        return new ResponseEntity(courseOfferingService.getAllCourseOffering(), HttpStatus.OK);

    }


    @RequestMapping(value={"/"}, method= RequestMethod.POST)
    public void addCourseOffering(@RequestBody CourseOfferingDTO courseOfferingDTO){

        System.out.println("courseOffering added successfully!!" +courseOfferingDTO.getEndDate());
        courseOfferingService.addCourseOffering(courseOfferingDTO);
    }


    @RequestMapping(value={"/{id}"}, method= RequestMethod.DELETE)
    public ResponseEntity deleteCourseOffering(@PathVariable int id){
        System.out.println("courseoffering deleting");
        if(courseOfferingService.deleteCourseOfferingByID(Long.valueOf(id))){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<CourseOfferingDTO> findCourseOfferingById (@PathVariable Long id){
        CourseOfferingDTO courseOfferingDTO=courseOfferingService.findCourseOfferingByID(id);
        if(courseOfferingDTO!=null){
            return new ResponseEntity<>(courseOfferingDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity updateCourseOffering(@RequestBody CourseOfferingDTO courseOfferingDTO){

       if( courseOfferingService.updateCourseOffering(courseOfferingDTO)){
           return new ResponseEntity(HttpStatus.ACCEPTED);
       }
       return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
