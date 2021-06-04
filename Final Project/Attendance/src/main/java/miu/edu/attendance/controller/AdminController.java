package miu.edu.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import miu.edu.attendance.service.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/approve")
    public Boolean approveFaculty(@RequestParam("faculty") Long id){
        return adminService.approveFaculty(id);
    }
}
