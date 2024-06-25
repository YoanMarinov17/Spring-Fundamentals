package com.dictionaryapp.service;


import com.dictionaryapp.config.LoggedUser;
import com.dictionaryapp.model.dto.UserLoginDto;
import com.dictionaryapp.model.dto.UserRegisterDto;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private final ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    private final LoggedUser loggedUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }

    public boolean register(UserRegisterDto data){
        if (!data.getPassword().equals(data.getConfirmPassword())){
        return false;
        }
        boolean isUserNameOrEmailTaken = userRepository.existsByUsernameOrEmail(data.getUsername(),data.getEmail());

        if (isUserNameOrEmailTaken){
            return false;
        }

        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean login(UserLoginDto data) {

        Optional<User> byUsername = userRepository.findUserByUsername(data.getUsername());

        if (byUsername.isEmpty()){
            return false;
        }

        User user = byUsername.get();


        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())){
            return false;
        }

        loggedUser.loginUser(user);

        return true;
    }

    public void logout() {
        loggedUser.logout();
    }
}
