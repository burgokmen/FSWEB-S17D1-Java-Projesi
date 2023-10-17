package com.brutech.spring.controller;

import com.brutech.spring.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animal")
public class AnimalController {
   private Map<Integer, Animal> animals = new HashMap<>();

   @PostConstruct
   public void init() {
       animals = new HashMap<>();
   }

   @GetMapping("/")
   public List<Animal> findAll() {
       return animals.values().stream().toList();
   }

    @GetMapping("/{id}")
   public Animal find(@PathVariable Integer id) {
       return animals.get(id);
   }

   @PostMapping("/")
   public Animal save(@RequestBody Animal animal) {
       animals.put(animal.getId(), animal);
       return animals.get(animal.getId());
   }





}
