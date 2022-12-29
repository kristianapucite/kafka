package com.example.models;

import lombok.Data;

@Data
public abstract class GeneralModel {

    private String fromTopic;
    private String toTopic;
    private String typeOfRequest;
}
