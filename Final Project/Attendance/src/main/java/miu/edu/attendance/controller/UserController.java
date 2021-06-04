package miu.edu.attendance.controller;


import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.domain.User;
import miu.edu.attendance.dto.FacultyDTO;
import miu.edu.attendance.dto.NewUser;
import miu.edu.attendance.dto.StudentDTO;
import miu.edu.attendance.dto.UserDTO;
import miu.edu.attendance.service.FacultyService;
import miu.edu.attendance.service.StudentService;
import miu.edu.attendance.service.UserDetailsImpl;
import miu.edu.attendance.service.UserDetailsServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    StudentService studentService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping({ "/current" })
    public @ResponseBody UserDTO getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
        return modelMapper.map(userdetails.getUser(), UserDTO.class);
    }
    @GetMapping({ "/mystudentinfo" })
    public @ResponseBody StudentDTO getCurrentStudent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
        List<Student> studentList  = studentService.findAll();
        Optional<Student> student = studentList
                                .stream()
                                .filter(s -> s.getUser().getUsername().compareToIgnoreCase(userdetails.getUsername()) == 0).findFirst();
        if(student.isPresent())
            return modelMapper.map(student.get(), StudentDTO.class);
        return null;
    }

    @GetMapping({ "/myfacultyinfo" })
    public @ResponseBody
    FacultyDTO getCurrentBuyer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userdetails = (UserDetailsImpl) auth.getPrincipal();
        Optional<Faculty> faculty =  facultyService.findAll().stream().filter(x->x.getUser().getUsername().equalsIgnoreCase(userdetails.getUsername())).findFirst();
        if(faculty.isPresent())
            return modelMapper.map(faculty.get(), FacultyDTO.class);
        return null;
    }

    @PostMapping("/update")
    public @ResponseBody  UserDTO updateSellerProfile(@RequestBody  NewUser updateUser){
        User user = userDetailsService.updateProfile(updateUser);
        if(user != null)
            return modelMapper.map(user, UserDTO.class);
        return null;
    }

}
