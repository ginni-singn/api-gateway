package com.ginni.quiz_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//we need primry key,title,question
//1 quiz = 1 table = multi que and multiple quiz= multipl table

@Entity//as we want a table creation
@Data

public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    //@ManyToMany
    //since return type is int, many to many wont work
    @ElementCollection
    private List<Integer> questionIds;


}
