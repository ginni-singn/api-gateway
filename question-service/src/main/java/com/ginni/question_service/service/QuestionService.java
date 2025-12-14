package com.ginni.question_service.service;

import com.ginni.question_service.dao.QuestionDao;
import com.ginni.question_service.model.Question;
import com.ginni.question_service.model.QuestionWrapper;
import com.ginni.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// to make a class which has similar stucture of the rows in table ,there4 we create Model class
//Question - fields=cols
@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions()//fetch data from DAO layer there4 create DAO class
    {
        //no method like getAllQuestions isthere there4 using inbuilt method
        //ResponseEntity return the 2 param(1=data we want to return, 2=Http Status) and to handle error we use try catch
        try{
        return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);//findall gives list of questions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionByCategory(String category) {
        //no predefined method to fetch data on the basis of category, so go to dao to write a method
        return questionDao.findByCategory(category); // fetch the method findBYCategory from the dao layer
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFormId(List<Integer> questionids) {
        List<QuestionWrapper> qw = new ArrayList<>();
        //his cant be empty, we need to send quested based on there ids
        List<Question> q = new ArrayList<>();//empty
        //fill q with the help of db    get 1 question at a time
        for(Integer i:questionids){
            q.add(questionDao.findById(i).get());//adding each question to list by ID
        }
        //wedont want questions but we want wrapper, so we can iteration ques and from this we will copy it in wrrper
        for(Question j: q){
            //creating 1 ques wrapper at a time
            QuestionWrapper qwpr = new QuestionWrapper();
            //and adding it to the list
            qwpr.setId(j.getId());
            qwpr.setQuestionTitle(j.getQuestionTitle());
            qwpr.setOption1(j.getOption1());
            qwpr.setOption2(j.getOption2());
            qwpr.setOption3(j.getOption3());
            qwpr.setOption4(j.getOption4());
            qw.add(qwpr);
        }
        return new ResponseEntity<>(qw,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int count=0;
        for(Response r: responses){
            Question question = questionDao.findById(r.getId()).get();
            if(r.getResponse().equals(question.getRightAnswer()))
                count++;

        }
        return new ResponseEntity<>(count,HttpStatus.OK);

    }

    // this above created question service will be used by the the quiz service
}
