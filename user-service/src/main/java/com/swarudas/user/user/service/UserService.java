package com.swarudas.user.user.service;

import com.swarudas.user.user.DTO.Department;
import com.swarudas.user.user.DTO.UserDepartmentVO;
import com.swarudas.user.user.entity.User;
import com.swarudas.user.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        log.info("Inside saveUser method of UserService...");
        return userRepository.save(user);
    }

    public UserDepartmentVO getUserWithDepartment(Long userId) {
        UserDepartmentVO vo = new UserDepartmentVO();
        User user = userRepository.getByUserId(userId);

        Department department = restTemplate.getForObject("http://localhost:9001/departments/" + user.getDepartmentId(),
                Department.class);

        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
