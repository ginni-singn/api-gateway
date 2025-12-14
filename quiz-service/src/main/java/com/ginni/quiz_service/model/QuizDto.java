package com.ginni.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    String category;
    Integer noOfQuestions;
    String title;
}
