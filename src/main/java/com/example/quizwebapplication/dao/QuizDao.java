package com.example.quizwebapplication.dao;

import com.example.quizwebapplication.domain.QuizResult;
import com.example.quizwebapplication.dto.quizResult.QuizResultDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class QuizDao extends AbstractHibernateDao<QuizResult> {
    public QuizDao() {
        setClazz(QuizResult.class);
    }

    @Transactional
    public List<QuizResult> getQuizResultByUserId(int id){
        Session session = this.getCurrentSession();
        String hql = "FROM QuizResult WHERE QuizResult.user.id = :userId";
        Query<QuizResult> query = session.createQuery(hql);
        query.setParameter("userId", id);
        List<QuizResult> quizResults = query.getResultList();
        return quizResults;
    }

    @Transactional
    public List<QuizResultDTO> getQuizzesResultDtoById(int id){
        Session session = this.getCurrentSession();
        String hql = "SELECT new com.example.quizwebapplication.dto.quizResult.QuizResultDTO(q.quizResultId, q.category.categoryId, q.startTime, q.endTime) FROM QuizResult q WHERE q.user.id = :userId";
        Query<QuizResultDTO> query = session.createQuery(hql);
        query.setParameter("userId", id);
        List<QuizResultDTO> quizResults = query.getResultList();
        return quizResults;
    }

    @Transactional
    public List<QuizResultDTO> getAllQuizzesResultDto(){
        Session session = this.getCurrentSession();
        String hql = "SELECT new com.example.quizwebapplication.dto.quizResult.QuizResultDTO(q.user.id, q.user.firstName, q.user.lastName, q.category.name, q.quizResultId, q.category.categoryId, q.startTime, q.endTime) FROM QuizResult q";
        Query<QuizResultDTO> query = session.createQuery(hql);
        List<QuizResultDTO> quizResults = query.getResultList();
        return quizResults;
    }
    @Transactional
    public void saveQuizResult(QuizResult quizResult){
        this.add(quizResult);
    }
}
