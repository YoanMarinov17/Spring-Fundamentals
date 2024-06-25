package com.example.shopping.service;

import com.example.shopping.model.dtos.UserLoginDTO;
import com.example.shopping.model.dtos.UserRegistrationDTO;
import com.example.shopping.model.entity.User;
import com.example.shopping.model.user.CurrentUser;
import com.example.shopping.repository.UserRepository;
import com.example.shopping.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(CurrentUser currentUser, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserRegistrationDTO userRegistrationDTO) {
        User user = this.modelMapper.map(userRegistrationDTO,User.class);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {
        this.currentUser.setUsername(userLoginDTO.getUsername());
        this.currentUser.setLoggedIn(true);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findCurrendUser() {
        return this.userRepository.findByUsername(currentUser.getUsername());
    }
}
