package com.swarudas.user.user.controller;

import com.swarudas.user.user.DTO.Department;
import com.swarudas.user.user.DTO.UserDepartmentVO;
import com.swarudas.user.user.entity.User;
import com.swarudas.user.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private static final String SERVICE_A = "serviceA";

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    int count = 1;
    @GetMapping("/{id}")
//    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "userFallback")
    @Retry(name = SERVICE_A)
    public UserDepartmentVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Retry method called {} times at {}",count++,new Date());
        return userService.getUserWithDepartment(userId);
    }

    public UserDepartmentVO userFallback(Exception e) {
        System.out.printf("Inside userFallback method of UserController..");
        UserDepartmentVO vo = new UserDepartmentVO();
        User user = User.builder()
                .userId(1234l)
                .firstName("dummy")
                .lastName("dummy")
                .email("dummy@email")
                .departmentId(1234l)
                .build();

        Department department = Department.builder()
                .departmentId(12l)
                .departmentCode("1234")
                .departmentName("dummyDept")
                .departmentAddress("dummyAdd")
                .build();

        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
