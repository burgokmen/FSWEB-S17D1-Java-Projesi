package com.brutech.spring.controller;

import com.brutech.spring.entity.Animal;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AnimalController {
   private Map<Integer, Animal> animals = new HashMap<>();


}
