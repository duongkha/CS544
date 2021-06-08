package miu.edu.attendance.controller;

import miu.edu.attendance.dto.StudentRegistrationDTO;
import miu.edu.attendance.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("studentRegistration")
public class StudentRegistrationController {

    @Autowired
    StudentRegistrationService studentRegistrationService;

    @RequestMapping(value={"/register"}, method= RequestMethod.POST)
    public ResponseEntity registerCourse(@RequestBody StudentRegistrationDTO studentRegistrationDTO){
        System.out.println("testing studentRegistratonDto");
        if(studentRegistrationService.register(studentRegistrationDTO)){
            return  new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
