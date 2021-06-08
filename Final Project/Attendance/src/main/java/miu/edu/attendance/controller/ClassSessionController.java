package miu.edu.attendance.controller;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.dto.ClassSessionDTO;
import miu.edu.attendance.service.ClassSessionService;
import miu.edu.attendance.service.ClassSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classsessions")
public class ClassSessionController {


    @Autowired
    ClassSessionService classSessionService;

    @GetMapping("/")
    public List<ClassSession> getAllClassSessions(){
       return classSessionService.getAllClassSessions();
    }


    @PostMapping ("/")
    public ResponseEntity addClassSession(@RequestBody ClassSessionDTO classSessionDTO){
         if(classSessionService.addClassSession(classSessionDTO)){
             return new ResponseEntity(HttpStatus.ACCEPTED);
         }
         return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
public ResponseEntity <ClassSession> getClassSessionById (@PathVariable Long id){
        ClassSession classSession=classSessionService.findTClassSessionByID(id);
        if(classSession!=null){
            return new ResponseEntity<>(classSession,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
