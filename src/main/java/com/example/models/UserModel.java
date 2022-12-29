package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UserModel extends GeneralModel{
    private String name;
    private String surname;
    private String email;
    private LocalDate dob;
}
