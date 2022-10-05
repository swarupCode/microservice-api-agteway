package com.swarudas.user.user.DTO;

import com.swarudas.user.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDepartmentVO {
    private User user;
    private Department department;
}
