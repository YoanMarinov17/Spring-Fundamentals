package com.example.shopping.service.interfaces;

import com.example.shopping.model.dtos.UserLoginDTO;
import com.example.shopping.model.dtos.UserRegistrationDTO;
import com.example.shopping.model.entity.User;

public interface UserService {
    void saveUser(UserRegistrationDTO userRegistrationDTO);

    void loginUser(UserLoginDTO userLoginDTO);

    User findByUsername(String username);

    User findCurrendUser();
}
