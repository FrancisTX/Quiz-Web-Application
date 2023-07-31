package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.domain.Category;
import com.example.quizwebapplication.domain.QuizQuestion;
import com.example.quizwebapplication.domain.QuizResult;
import com.example.quizwebapplication.domain.User;
import com.example.quizwebapplication.dto.category.CategoryDTO;
import com.example.quizwebapplication.dto.category.CategoryResponse;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import com.example.quizwebapplication.dto.question.QuestionDTO;
import com.example.quizwebapplication.dto.quiz.*;
import com.example.quizwebapplication.dto.quizResult.QuizResultDTO;
import com.example.quizwebapplication.dto.user.UserResponse;
import com.example.quizwebapplication.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizController {
    private QuestionService questionService;
    private CategoryService categoryService;
    private QuizService quizResultService;
    private UserService userService;
    @Autowired
    public QuizController(QuestionService questionService, CategoryService categoryService,
                          QuizService quizResultService, UserService userService) {
        this.questionService = questionService;
        this.categoryService = categoryService;
        this.quizResultService = quizResultService;
        this.userService = userService;
    }

    //show all the categories
    @GetMapping("/categories")
    public CategoryResponse getQuizSelection() {
        List<CategoryDTO> categories = categoryService.getAllCategoryNames();
        return CategoryResponse.builder()
                .status(ResponseStatus.builder()
                        .success(true)
                        .message("Got the categories")
                        .build())
                .categories(categories)
                .build();
    }

    //pick 5 random question from the category
    @GetMapping("/quiz")
    public QuizQuestionResponse getQuiz(@RequestParam int categoryId) {
        List<QuestionDTO> questions = questionService.getQuestionByCategoryId(categoryId);

        List<QuestionWithChoicesDTO> res = new ArrayList<>();

        for(QuestionDTO question : questions) {
            res.add(QuestionWithChoicesDTO.builder()
                    .question(question)
                    .choices(questionService.getChoiceByQuestionId(question.getQuestionId()))
                    .build());
        }
        return QuizQuestionResponse.builder()
                .status(ResponseStatus.builder()
                        .success(true)
                        .message("Got the questions")
                        .build())
                .QuestionsWithChoices(res)
                .build();
    }

    @GetMapping("/quiz/{userId}")
    public QuizResultResponse getQuizResult(@PathVariable int userId){
        List<QuizResultDTO> res =  quizResultService.getQuizzesResultDtoById(userId);
        return QuizResultResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! Quiz results found")
                                .build()
                )
                .quizResults(res)
                .build();
    }

    @GetMapping("/quizzes")
    public AllQuizResultResponse getAllQuizResult(){
        List<QuizResultDTO> res =  quizResultService.getAllQuizzesResultDto();

        return AllQuizResultResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! Quiz results found")
                                .build()
                )
                .quizResults(res)
                .build();
    }

    @PostMapping("/quiz")
    public ResponseStatus submitQuiz(@Valid @RequestBody QuizSubmissionRequest request, BindingResult bindingResult){
        UserResponse errorResponse = userService.checkInput(bindingResult);
        if(errorResponse != null){
            return ResponseStatus.builder()
                    .success(false)
                    .message("Error to submit quiz result")
                    .build();
        }

        User user = userService.getUserById(request.getUserId());
        Category category = categoryService.getCategoryById(request.getCategoryId());
        //insert quiz result
        QuizResult quizResult = QuizResult.builder()
                .user(user)
                .category(category)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .quizQuestions(new ArrayList<>())
                .build();

        //insert each quiz question and user choice
        for (SingleQuizResult q : request.getResult()){
            QuizQuestion quizQuestion = QuizQuestion.builder()
                    .quizResult(quizResult)
                    .question(questionService.getQuestionById(q.getQuestionId()))
                    .choice(questionService.getChoiceById(q.getChoiceId()))
                    .build();
            quizResult.getQuizQuestions().add(quizQuestion);
        }
        quizResultService.saveQuizResult(quizResult);
        return ResponseStatus.builder()
                .success(true)
                .message("Quiz result saved")
                .build();
    }

    @GetMapping("/choice/{choiceId}")
    public ResponseStatus getChoiceById(@PathVariable int choiceId){
        //System.out.println(questionService.getChoiceById(choiceId));
        return ResponseStatus.builder()
                .success(true)
                .message("Got the choice")
                .build();
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseStatus getCategoryById(@PathVariable int categoryId){
        Category category = categoryService.getCategoryById(categoryId);
        return ResponseStatus.builder()
                .success(true)
                .message(category.getName())
                .build();
    }

    //using for showing result in the quiz result page
    @GetMapping("/questions/{quizResultId}")
    public QuizQuestionsResultResponse getQuestionByQuizResultId(@PathVariable int quizResultId){
        List<QuizQuestion> quizQuestion = questionService.getQuizQuestionsByQuizResultId(quizResultId);

        List<QuizQuestionsDTO> res = new ArrayList<>();
        for(QuizQuestion q : quizQuestion) {
            QuizQuestionsDTO cur = QuizQuestionsDTO.builder()
                    .questionsDto(questionService.getQuestionDTOById(q.getQuestion().getQuestionId()))
                    .choiceDto(questionService.getChoiceDTOById(q.getChoice().getChoiceId()))
                    .choices(questionService.getChoiceByQuestionId(q.getQuestion().getQuestionId()))
                    .build();
            res.add(cur);
        }

        return QuizQuestionsResultResponse.builder()
                .quizQuestions(res)
                .status(ResponseStatus.builder()
                        .success(true)
                        .message("Got the questions")
                        .build())
                .build();
    }
}
