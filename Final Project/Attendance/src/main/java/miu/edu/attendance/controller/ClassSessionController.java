package miu.edu.attendance.controller;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.dto.ClassSessionRequestDTO;
import miu.edu.attendance.dto.ClassSessionResponseDTO;
import miu.edu.attendance.service.ClassSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classsessions")
public class ClassSessionController {


    @Autowired
    ClassSessionService classSessionService;

    @GetMapping("/")
    public List<ClassSessionResponseDTO> getAllClassSessions(){
       return classSessionService.getAllClassSessions();
    }


    @PostMapping ("/")
    public ResponseEntity addClassSession(@RequestBody ClassSessionRequestDTO classSessionRequestDTO){
         if(classSessionService.addClassSession(classSessionRequestDTO)){
             return new ResponseEntity(HttpStatus.ACCEPTED);
         }
         return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity deleteClasssessionById (@PathVariable Long id){
        if(classSessionService.deleteClassSession(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping ("/")
    public ResponseEntity updateClasssession(@RequestBody ClassSessionRequestDTO classSessionRequestDTO){
        if(classSessionService.updateClassSession(classSessionRequestDTO)){
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClassSessionResponseDTO> findClassSessionById(@PathVariable Long id){
        ClassSessionResponseDTO classSessionResponseDTO=classSessionService.findTClassSessionByID(id);
       if( classSessionResponseDTO!=null){
           return new ResponseEntity<>(classSessionResponseDTO,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
