package com.ginni.quiz_service.feign;

import com.ginni.quiz_service.model.QuestionWrapper;
import com.ginni.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//()- which service you are connecting to
@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    //which methods are you going to call- copy it from question-service, since it is an
    // interface, only method declaration
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName, @RequestParam Integer numQuestions);


    //getquestions = quiz service asking for questions of a particular id
    //return questions and there options when we provide the ids in body as post request
    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFormId(@RequestBody List<Integer> questionids);


    //getScore = return a score and get a score of the quiz
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
