package com.ginni.quiz_service.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//we need the response because client will send the responses means id and answer in the
// form with the help of response only

@Data
@RequiredArgsConstructor
public class Response {
    private Integer id;
    private String response;
}
