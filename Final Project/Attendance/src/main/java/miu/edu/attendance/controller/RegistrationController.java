package miu.edu.attendance.controller;

import miu.edu.attendance.dto.NewUser;
import miu.edu.attendance.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/signup")
public class RegistrationController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping
    public String register(@RequestBody NewUser user){

        return userDetailsService.signUpUser(user);
    }
}
