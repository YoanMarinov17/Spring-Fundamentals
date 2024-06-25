package com.bonappetit.config;


import com.bonappetit.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private long id;
    private String username;


    public void loginUser(long id, String username) {
        this.id = id;
        this.username = username;
    }


    // Проверява дали user-а е логнат или не.
    // Ако id е различно от 0, значи е логнат.
    public boolean isUserLoggedIn(){
        return id > 0;
    }

    public String username(){
        return username;
    }


    public Long id() {
        return id;
    }

    public void logout() {
        this.id = 0;
        this.username = "";
    }




}
