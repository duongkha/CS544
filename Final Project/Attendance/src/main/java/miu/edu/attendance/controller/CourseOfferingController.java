package miu.edu.attendance.controller;

import miu.edu.attendance.dto.CourseOfferingDTO;
import miu.edu.attendance.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseOfferings")
public class CourseOfferingController {

@Autowired
CourseOfferingService courseOfferingService;


    @GetMapping(value = "/")
    public ResponseEntity<List<CourseOfferingDTO>> getAllCourseOfferings(){
        return new ResponseEntity(courseOfferingService.getAllCourseOffering(), HttpStatus.OK);

    }


    @RequestMapping(value={"/courseOffering"}, method= RequestMethod.POST)
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

}
