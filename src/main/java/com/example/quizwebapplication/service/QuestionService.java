package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dao.ChoiceDao;
import com.example.quizwebapplication.dao.QuestionDao;
import com.example.quizwebapplication.domain.Choice;
import com.example.quizwebapplication.domain.Question;
import com.example.quizwebapplication.domain.QuizQuestion;
import com.example.quizwebapplication.dto.choice.ChoiceDTO;
import com.example.quizwebapplication.dto.question.EditQuestionResponse;
import com.example.quizwebapplication.dto.question.QuestionDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    private QuestionDao questionDao;
    private ChoiceDao choiceDao;

    public QuestionService(QuestionDao questionDao, ChoiceDao choiceDao) {
        this.questionDao = questionDao;
        this.choiceDao = choiceDao;
    }

    @Transactional
    public void addChoice(Choice choice) {
        choiceDao.saveChoice(choice);
    }
    @Transactional
    public void addQuestion(Question question) {
        questionDao.addQuestion(question);
    }
    @Transactional
    public List<QuestionDTO> getQuestionByCategoryId(int category_id) {
        List<QuestionDTO> questions = questionDao.getQuestionByCategoryId(category_id);
        //select 5 random questions
        Collections.shuffle(questions);
        return questions.subList(0, 5);
    }

    @Transactional
    public List<ChoiceDTO> getChoiceByQuestionId(int question_id) {
        return questionDao.getChoiceByQuestionId(question_id);
    }

    @Transactional
    public Question getQuestionById(int question_id) {
        return questionDao.getQuestionById(question_id);
    }

    @Transactional
    public Choice getChoiceById(int choice_id) {
        return choiceDao.getChoiceById(choice_id);
    }

    @Transactional
    public ChoiceDTO getChoiceDTOById(int choice_id) {
        return choiceDao.getChoiceDTOById(choice_id);
    }

    @Transactional
    public QuestionDTO getQuestionDTOById(int question_id) {
        return questionDao.getQuestionDTOById(question_id);
    }

    @Transactional
    public List<QuizQuestion> getQuizQuestionsByQuizResultId(int quiz_result_id) {
        return questionDao.getQuizQuestionsByQuizResultId(quiz_result_id);
    }

    @Transactional
    public List<QuestionDTO> getAllQuestionDTO(){
        System.out.println("start getAllQuestionDTO");
        return questionDao.getAllQuestionDTO();
    }

    @Transactional
    public void activeOrSuspendQuestionById(int id, boolean isActive){
        questionDao.activeOrSuspendQuestionById(id, isActive);
    }

    @Transactional
    public void saveEditedQuestion(int questionId, EditQuestionResponse editQuestionRequest){
        Question question = this.getQuestionById(questionId);
        question.setContent(editQuestionRequest.getDescription());

        for(int i = 0; i < editQuestionRequest.getChoices().size(); i++){
            ChoiceDTO currChoice = editQuestionRequest.getChoices().get(i);
            Choice choice = this.getChoiceById(currChoice.getChoiceId());

            choice.setDescription(currChoice.getDescription());

            if(i == editQuestionRequest.getCorrectAnswer()-1){
                choice.setCorrect(true);
            }else{
                choice.setCorrect(false);
            }
        }
        questionDao.updateQuestion(question);
    }
}
