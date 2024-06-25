package com.bonappetit.service;


import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.entity.dto.UserLoginDto;
import com.bonappetit.model.entity.dto.UserRegisterDto;
import com.bonappetit.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder;

    private LoggedUser loggedUser;


    public UserService(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, LoggedUser loggedUser) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
    }


    public boolean register(UserRegisterDto data) {

        // Проверка дали паролата и confirm паролата са еднакви
        // Ако Не са еднакви - връша false
        // Ako са еднакви - продължаваме напред
        if (!data.getPassword().equals(data.getConfirmPassword())) {
            return false;
        }

        // Проверка дали username и email са заети.
        // Ако са заети - връща false
        boolean isUserNameOrEmailTaken = userRepo.existsByUsernameOrEmail(data.getUsername(), data.getEmail());

        if (isUserNameOrEmailTaken) {
            return false;
        }

        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }


    public boolean login(UserLoginDto data) {
        Optional<User> findByUsername = userRepo.findByUsername(data.getUsername());


        //Ако няма такъв user в базата данни, значи той не е регистриран и ще върне false
        if (findByUsername.isEmpty()){
            return false;
        }

        User user = findByUsername.get();

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())){
            return false;
        }

        loggedUser.loginUser(user.getId(), user.getUsername());


        return true;


    }

    @Transactional
    public List<Recipe> findFavourites(Long id) {
//
//        return userRepo.findById(id).map(User ::getFavouriteRecipes)
//                .orElseGet(ArrayList::new);

        Optional<User> byId = userRepo.findById(id);

        if (byId.isEmpty()){
            return new ArrayList<>();
        }

        return byId.get().getFavouriteRecipes();

    }
}
