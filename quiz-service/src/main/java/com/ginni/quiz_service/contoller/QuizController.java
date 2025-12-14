package com.ginni.quiz_service.contoller;

//creating it for this URL - http://localhost:8080/quiz/create?category=Java&noOfQuestions=5&title=JQuiz
//quiz(Controller call)--> create(method)-POST request as it accept some value like Category,no. of ques ,Title-weare sending these

import com.ginni.quiz_service.model.QuestionWrapper;
import com.ginni.quiz_service.model.QuizDto;
import com.ginni.quiz_service.model.Response;
import com.ginni.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    //since we want response with type string
    //instead of taking 3 values, creating a single object QuizDTO and feeding the 3 values over there
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        // while sending the data object.getXXX
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNoOfQuestions(),quizDto.getTitle());
    }

    //till here we had 3 tables= Question(standard) new made quiz_id(cols= quiz_id=1 & quiz_title= JQuiz)
    //other table question_id(col= quiz id(all rows) =1 and question_id = 5 random question id with javacategory)

    //to fetch the questions also
    // get request = http://localhost:8080/quiz/get/1 "1 is id of the quiz"
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable Integer id){
        //since the list will send the list with right ans also there4 we will create Questionwrapper
        // class so that right choice anddifficulty is not sent
        return quizService.getQuizQuestions(id);

    }
    //Now quiz is fetched now we have to calculate and send back the results
    // for this we do a post request with http://localhost:8080/quiz/submit/1 and raw data
    // responses = {"id" :18,"response":"4"} etc for all the ques of that id there4 list
    //so this should be send to the server and server should calculate and send back the result
    // having all id and response as an Integer value

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizService.calculateResult(id,responses);
    }

}
