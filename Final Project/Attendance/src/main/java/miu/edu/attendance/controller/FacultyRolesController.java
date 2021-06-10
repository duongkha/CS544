package miu.edu.attendance.controller;

import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.dto.CourseOfferingDTO;
import miu.edu.attendance.service.AttendanceService;
import miu.edu.attendance.service.FacultyRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("facultyroles")
public class FacultyRolesController {

    @Autowired
    FacultyRolesService assignCourseToFacultyControllerService;

    @Autowired
   FacultyRolesService facultyRolesService;

    @PostMapping("/")
    public ResponseEntity addCourse(@RequestParam("facultyId") Long facultyId,@RequestParam("courseOfferingId") Long courseOfferingId){
    if(assignCourseToFacultyControllerService.addCourseToFaculty(facultyId,courseOfferingId)){
        return new ResponseEntity(HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }




    @GetMapping
    public List<AttendanceRecordDTO> getAttendanceReportByFacultyId(@RequestParam("facultyid") Long facultyid,
                                                                  @RequestParam("courseofferingid") Long courseofferingid,
                                                                  @RequestParam("page") Long pageNo, @RequestParam("size") Long size){
        List<AttendanceRecordDTO> attendances =  facultyRolesService.findAllAttendanceByFacultyIdAndCourseOffering(facultyid, courseofferingid) .stream()
                .skip((pageNo - 1) * size).limit(size).collect(Collectors.toList());

        return attendances;
    }
    @GetMapping("/{facultyid}")
    public ResponseEntity< List<CourseOfferingDTO>> getAllFacultyCourseOffering(@PathVariable("facultyid") Long facultyid){

        List<CourseOfferingDTO> courseOfferingDTOS= facultyRolesService.findAllCourseOfferingsByFacultyId(facultyid);
        if(courseOfferingDTOS!=null){
            return new ResponseEntity<>(courseOfferingDTOS,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
