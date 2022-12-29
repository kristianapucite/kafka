package com.example.services;

import com.example.models.UserModel;
import com.example.repositories.UserDAO;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    UserModel getById(Long id);
    UserModel saveUser(String name, String surname, String email, LocalDate dob);
    List<UserModel> getAllUsers();
    UserModel updateUser(Long id, String name, String surname, String email, LocalDate dob);
    boolean deleteUser(Long id);
}
