package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dao.QuizDao;
import com.example.quizwebapplication.domain.QuizResult;
import com.example.quizwebapplication.dto.quizResult.QuizResultDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuizService {
    private QuizDao quizDao;
    public QuizService(QuizDao quizDao){
        this.quizDao = quizDao;
    }
    @Transactional
    public List<QuizResult> getAllQuizzesById(int Id) {
        return quizDao.getQuizResultByUserId(Id);
    }

    @Transactional
    public List<QuizResultDTO> getAllQuizzesResultDto() {
        return quizDao.getAllQuizzesResultDto();
    }

    @Transactional
    public List<QuizResultDTO> getQuizzesResultDtoById(int Id) {
        return quizDao.getQuizzesResultDtoById(Id);
    }

    @Transactional
    public void saveQuizResult(QuizResult quizResult) {
        quizDao.saveQuizResult(quizResult);
    }
}
