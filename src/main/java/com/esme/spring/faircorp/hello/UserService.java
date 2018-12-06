package com.esme.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    @Autowired
    void greetAll();
}