package com.esme.spring.faircorp.hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService {

    @Autowired
    GreetingService gs;

    public void greetAll(){

        String[] list={"mimi","moustache"};
        gs.greet(list[0]);
        gs.greet(list[1]);
    }

}
