package com.example.controllers;


import com.example.models.UserModel;
import com.example.services.UserService;
import com.github.shihyuho.jackson.databind.SerializeAllExcept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
//@SerializeAllExcept({"fromTopic", "toTopic", "typeOfRequest"})
@RequestMapping(value = "api/v1/users")
public class UserController {

    @Value("${spring.kafka.topic.name}")
    private String kafkaTopicName;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired private UserService service;


    public UserController(
            @Value("${spring.kafka.topic.name}") String kafkaTopicName,
            KafkaTemplate<String, String> kafkaTemplate) {
        super();
        this.kafkaTopicName = kafkaTopicName;
        this.kafkaTemplate = kafkaTemplate;
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(
            @PathVariable Long id) {
        UserModel model = service.getById(id);
        if(model != null) {
            kafkaTemplate.send(kafkaTopicName, "user found");
            return ResponseEntity.ok(model);
        }
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<UserModel> saveUser(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam(required = false) String email,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob
            ) {

        UserModel model = service.saveUser(name, surname, email, dob);
        if(model != null) {
            return ResponseEntity.ok(model);
        }
        else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam(required = false) String email,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob
    ) {

        UserModel model = service.updateUser(id, name, surname, email, dob);
        if(model != null) {
            return ResponseEntity.ok(model);
        }
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = service.getAllUsers();
        if(!users.isEmpty()) {
            return ResponseEntity.ok(users);
        }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(
            @PathVariable Long id
    ) {
        boolean isDeleted = service.deleteUser(id);
        if(isDeleted) {
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
    }

}

