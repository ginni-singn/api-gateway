package com.ginni.question_service.dao;


import com.ginni.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//this has all 7 steps of jdbc- creating session ,fire sql query,saving data---- to avoid this , we change
//class QuestionDao to interface QuestionDao to extend JPARepository- all 7 steps handlesby this
@Repository
public interface QuestionDao extends JpaRepository<Question,Integer>
//JPARepo<TableName, Primary Key type here>
{
    //fetch data base on category- no sql or method written as JPA is smart enough to understand that by this -
    // findByCategory that filter is put on category
    //typeof data returned + methd name + parameter
    List<Question> findByCategory(String category);


    //jpa cannot provide if we have somany filter like category + no.of que there4 we write native query not sql
    @Query(value = "SELECT q.id FROM question q where q.category =:category ORDER BY RANDOM() LIMIT :noOfQuestions",nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int noOfQuestions);
}
