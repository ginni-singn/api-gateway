package com.ginni.question_service.contoller;

import com.ginni.question_service.model.Question;
import com.ginni.question_service.model.QuestionWrapper;
import com.ginni.question_service.model.Response;
import com.ginni.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//this controller only handles the questions -- ACCEPTS REQUEST FROM USER, USER WANTS 2+3 , CONTLR
//WILL SEND 2+3 TO SERVICE TOPRODUCE 5(S.L. -WHAT PROSECCING NEEDS TO BE PERFORMED) --DATA TAKEN
//FROM DB USING REPO LAYER

@RestController//called to accept the request
@RequestMapping("question") //accept the request when "question" is triggered
public class QuestionController

{
    @Autowired //in place of "new" objt creation
    QuestionService questionService;//objct of que service

    @GetMapping("allQuestions")//feth all questions frpm db
    //put ResponsiEntity to get all the question with the standadrd status code, which is defined in Service layer
    public ResponseEntity<List<Question>> getAllQuestions()//accept request of "/questions" and return string
            //return list of Questions(class Model)
    {
        return questionService.getAllQuestions(); // Cntlr methos = SL method most of the case
    }


    @GetMapping("category/{category}")//{variable putting}
    public List<Question> getQuestionByCategory(@PathVariable String category)//getting only question of java or paython based on the category
    //@pathvariable tell us the parameter is used as variable in the mapping url. {variable} will be assigned to path variable - variable
    {
        return questionService.getQuestionByCategory(category);
    }

    //adding question . retrun type String = "question added" comment . since we recieve data in JSOn, we will be
    // sending data in JSON andsring will convert it into object
    //RequestBody-send data from client side to server  in the body

    @PostMapping("add") //question/add and json data is added via postman as it interact with api(localhost.....) can be done
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }

    //MS=Generate, basically it return the ques ids of the quiz
    //to gnrat a quiz we need 2 things- which category? and how many ques? so quiz service would ask
    //with these 2 params
    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName,@RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    //getquestions = quiz service asking for questions of a particular id
    //return questions and there options when we provide the ids in body as post request
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFormId(@RequestBody List<Integer> questionids){
        return questionService.getQuestionsFormId(questionids);
    }

    //getScore = return a score and get a score of the quiz
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}
