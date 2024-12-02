package com.security.spring_security.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String getAllEmployees(){
        return "You received All Employees List";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveEmployee(){
        return "You saved an Employee.";
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateEmployee(){
        return "You updated an Employee";
    }
}
