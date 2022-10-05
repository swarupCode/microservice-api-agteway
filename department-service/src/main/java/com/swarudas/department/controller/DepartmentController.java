package com.swarudas.department.controller;

import com.swarudas.department.entity.Department;
import com.swarudas.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.saveDepartment(department));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }
}
