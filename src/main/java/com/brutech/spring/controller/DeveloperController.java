package com.brutech.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeveloperController {
    @Value("${developer.name}")
    private String name;
    @Value("${developer.surname}")
    private String surname;

    @GetMapping("/developer")
    public String developer() {
        return name +" " + surname + " is a developer";
    }
}
