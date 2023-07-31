package com.example.quizwebapplication.dao;

import com.example.quizwebapplication.domain.Question;
import com.example.quizwebapplication.domain.QuizQuestion;
import com.example.quizwebapplication.dto.choice.ChoiceDTO;
import com.example.quizwebapplication.dto.question.QuestionDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDao extends AbstractHibernateDao<Question>{
    public QuestionDao() {
        setClazz(Question.class);
    }

    public void addQuestion(Question question) {
        this.add(question);
    }

    public void updateQuestion(Question question) {
        this.update(question);
    }
    public List<QuestionDTO> getQuestionByCategoryId(int category_id) {
        Session session = this.getCurrentSession();
        //String sql = "SELECT new com.example.resthibernatedemo.dto.question.QuestionDTO(q.questionId, q.category.categoryId, q.content, q.isActive, (SELECT new com.example.resthibernatedemo.dto.choice.ChoiceDTO(c.choiceId, c.description, c.isCorrect))) FROM Question q JOIN q.choices c WHERE q.category.categoryId = :category_id";
//        String sql = "SELECT new com.example.resthibernatedemo.dto.question.QuestionDTO(q.questionId, q.category.categoryId, q.content, q.isActive, " +
//                " (SELECT new com.example.resthibernatedemo.dto.choice.ChoiceDTO(c.choiceId, c.description, c.isCorrect) " +
//                " FROM Choice c WHERE c.question = q)) " +
//                " FROM Question q WHERE q.category.categoryId = :category_id";
        String sql = "SELECT new com.example.quizwebapplication.dto.question.QuestionDTO(q.questionId, q.category.categoryId, q.content, q.isActive) FROM Question q WHERE q.category.categoryId = :category_id";
        Query<QuestionDTO> query = session.createQuery(sql, QuestionDTO.class);
        query.setParameter("category_id", category_id);
        return query.getResultList();
    }

    public List<ChoiceDTO> getChoiceByQuestionId(int question_id) {
        Session session = this.getCurrentSession();
        String sql = "SELECT new com.example.quizwebapplication.dto.choice.ChoiceDTO(c.choiceId, c.description, c.isCorrect) FROM Choice c WHERE c.question.questionId = :question_id";
        Query<ChoiceDTO> query = session.createQuery(sql, ChoiceDTO.class);
        query.setParameter("question_id", question_id);
        return query.getResultList();
    }

    public Question getQuestionById(int id) {
        return this.findById(id);
    }

    public QuestionDTO getQuestionDTOById(int id) {
        Session session = this.getCurrentSession();
        String sql = "SELECT new com.example.quizwebapplication.dto.question.QuestionDTO(q.questionId, q.category.categoryId, q.content, q.isActive) FROM Question q WHERE q.questionId = :id";
        Query<QuestionDTO> query = session.createQuery(sql, QuestionDTO.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    public List<QuizQuestion> getQuizQuestionsByQuizResultId(int quizResultId) {
        Session session = this.getCurrentSession();
        String sql = "FROM QuizQuestion qq WHERE qq.quizResult.quizResultId = :quizResultId";
        Query<QuizQuestion> query = session.createQuery(sql, QuizQuestion.class);
        query.setParameter("quizResultId", quizResultId);
        return query.getResultList();
    }

    public List<QuestionDTO> getAllQuestionDTO(){
        System.out.println("start getAllQuestionDAO");
        Session session = this.getCurrentSession();
        String sql = "SELECT new com.example.quizwebapplication.dto.question.QuestionDTO(q.questionId, q.category.categoryId, q.content, q.isActive) FROM Question q";
        Query<QuestionDTO> query = session.createQuery(sql, QuestionDTO.class);
        System.out.println("end getAllQuestionDAO: " + query.getResultList());
        return query.getResultList();
    }

    public void activeOrSuspendQuestionById(int id, boolean isActive){
        Question question = this.getQuestionById(id);
        if(question == null){
            throw new RuntimeException("Question not found");
        }
        question.setIsActive(isActive);
        this.update(question);
    }
}
