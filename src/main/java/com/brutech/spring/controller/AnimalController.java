package com.brutech.spring.controller;

import com.brutech.spring.dto.AnimalResponse;
import com.brutech.spring.entity.Animal;
import com.brutech.spring.validation.AnimalValidation;
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
   public AnimalResponse findAll() {
       //return animals.values().stream().toList();
       return new AnimalResponse(animals.values().stream().toList().toString(), "Success", 200);
   }

    @GetMapping("/{id}")
   public AnimalResponse find(@PathVariable Integer id) {
        if(!AnimalValidation.isIdValid(id)){
            return new AnimalResponse(null, "Animal Id is not valid", 400);
        }
        if(!AnimalValidation.isAnimalContains(animals, id)){
            return new AnimalResponse(null, "Animal is not found with this id:" + id , 404);
        }

       return new AnimalResponse(animals.get(id).getName(), "Success", 200);
   }

   @PostMapping("/")
   public AnimalResponse save(@RequestBody Animal animal) {
       if (!AnimalValidation.isAnimalCredentialsValid(animal)){
           return new AnimalResponse(null, "Animal credentials are not valid", 400);
       }

       animals.put(animal.getId(), animal);
       return new AnimalResponse(animals.get(animal.getId()).getName(), "Success", 200);
   }

    @PutMapping("/{id}")
    public AnimalResponse update(@PathVariable Integer id, @RequestBody Animal animal) {
       animal.setId(id);
       if (!AnimalValidation.isIdValid(id)){
           return new AnimalResponse(null, "Animal Id is not valid", 400);
       }

        if(!AnimalValidation.isAnimalContains(animals, id)){
            return new AnimalResponse(null, "Animal is not found with this id:" + id , 404);
        }

        if (!AnimalValidation.isAnimalCredentialsValid(animal)){
            return new AnimalResponse(null, "Animal credentials are not valid", 400);
        }
       animals.put(id, animal);
       return new AnimalResponse(animals.get(id).getName(), "Success", 200);
    }

    @DeleteMapping("/{id}")
    public AnimalResponse delete(@PathVariable Integer id) {
         if (!AnimalValidation.isIdValid(id)){
              return new AnimalResponse(null, "Animal Id is not valid", 400);
         }
        if(!AnimalValidation.isAnimalContains(animals, id)){
            return new AnimalResponse(null, "Animal is not found with this id:" + id , 404);
        }
       return new AnimalResponse(animals.remove(id).getName(), "Success", 200);
    }




}
