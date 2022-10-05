package com.swarudas.user.user.controller;

import com.swarudas.user.user.DTO.UserDepartmentVO;
import com.swarudas.user.user.entity.User;
import com.swarudas.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDepartmentVO> getUserWithDepartment(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getUserWithDepartment(userId));
    }
}
