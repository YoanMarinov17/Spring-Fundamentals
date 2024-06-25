package com.planner.service;

import com.planner.model.dtos.UserLoginDTO;
import com.planner.model.dtos.UserRegistrationDTO;
import com.planner.model.entity.User;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    void loginUser(UserLoginDTO userLoginDTO);

    User findByUsername(String username);

    User findCurrendUser();
}
