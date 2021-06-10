package miu.edu.attendance.controller;

import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.dto.CourseOfferingDTO;
import miu.edu.attendance.dto.StudentRegistrationDTO;
import miu.edu.attendance.service.StudentRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("studentcourses")
public class StudentRolesController {

@Autowired
StudentRolesService studentRolesService;


    @PostMapping(value={"/register"})
    public ResponseEntity registerCourse(@RequestBody StudentRegistrationDTO studentRegistrationDTO){
        System.out.println("testing studentRegistratonDto");
        if(studentRolesService.register(studentRegistrationDTO)){
            return  new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


 @GetMapping("/courseofferings")
    public List<CourseOfferingDTO> getAllCourseOffering(@RequestParam("studentid" )Long id){
         return studentRolesService.getAllCourseOfferings(id);
    }
 @GetMapping("/attendances")
    public List<AttendanceRecordDTO> getAllAttendanceRecord(@RequestParam ("studentid") Long studentid, @RequestParam ("courseofferingid") Long id){
        return studentRolesService.getAttendanceRecordForCourse(studentid,id);
    }


}
