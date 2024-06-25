package com.example.shopping.init;

import jakarta.annotation.PostConstruct;

public interface DataBaseInitService {
    @PostConstruct
    void init();
}
