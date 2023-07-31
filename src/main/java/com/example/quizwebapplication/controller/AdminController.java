package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.domain.Choice;
import com.example.quizwebapplication.domain.Contact;
import com.example.quizwebapplication.domain.Question;
import com.example.quizwebapplication.domain.User;
import com.example.quizwebapplication.dto.choice.ChoiceRequest;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import com.example.quizwebapplication.dto.choice.ChoiceDTO;
import com.example.quizwebapplication.dto.contact.AllContactResponse;
import com.example.quizwebapplication.dto.question.*;
import com.example.quizwebapplication.dto.user.AllUserResponse;
import com.example.quizwebapplication.dto.user.UserDTO;
import com.example.quizwebapplication.dto.user.UserDtoResponse;
import com.example.quizwebapplication.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {
    private ContactService contactService;
    private QuizService quizService;
    private UserService userService;
    private QuestionService questionService;

    private CategoryService categoryService;

    public AdminController(ContactService contactService, QuizService quizService,
                           UserService userService, QuestionService questionService,
                           CategoryService categoryService) {
        this.contactService = contactService;
        this.quizService = quizService;
        this.userService = userService;
        this.questionService = questionService;
        this.categoryService = categoryService;
    }

    //get all contacts
    @GetMapping("/contacts")
    public AllContactResponse getContactUsManagement(){
        List<Contact> contacts = contactService.getAllContacts();
        return AllContactResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! Get All Contacts!")
                                .build()
                )
                .contacts(contacts)
                .build();
    }

    @PatchMapping("/user/{userId}/status")
    public UserDtoResponse activeOrSuspendUserById(@PathVariable int userId,
                                                   @RequestParam("activate") boolean isActive) {
        UserDTO userDto = userService.getUserDtoById(userId);
        System.out.println(isActive);
        if(userDto == null){
            return UserDtoResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Oops, user not found")
                                    .build()
                    )
                    .userDto(null)
                    .build();
        }
        //update user status
        userService.activeOrSuspendUserById(userId, isActive);

        userDto = userService.getUserDtoById(userId);
        return UserDtoResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! User status was updated")
                                .build()
                )
                .userDto(userDto)
                .build();
    }

    @PatchMapping("/question/{questionId}/status")
    public QuestionResponse activeOrSuspendQuestionById(@PathVariable int questionId,
                                                        @RequestParam("activate") boolean isActive) {
        //update question status
        questionService.activeOrSuspendQuestionById(questionId, isActive);

        QuestionDTO questionDTO = questionService.getQuestionDTOById(questionId);
        return QuestionResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success! Question status was updated")
                                .build()
                )
                .question(questionDTO)
                .build();
    }


    @GetMapping("/users")
    public AllUserResponse getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        List<User> res = new ArrayList<>();
        for(UserDTO user: users){
            User newUser = User.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .isActive(user.isActive())
                    .isAdmin(user.isAdmin())
                    .build();
            res.add(newUser);
        }

        return AllUserResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success!")
                                .build()
                )
                .users(res)
                .build();
    }

    @GetMapping("/questions")
    public AllQuestionResponse getAllQuestions() {
        List<QuestionDTO> res = questionService.getAllQuestionDTO();

        return AllQuestionResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Success!")
                                .build())
                .questions(res)
                .build();
    }

    @PostMapping("/questions")
    public ResponseStatus addQuestion(@RequestBody NewQuestionResponse newQuestionResponse) {
//        System.out.println(newQuestionResponse.getCategoryId());
//        System.out.println(newQuestionResponse.getDescription());
//        System.out.println(newQuestionResponse.getCorrectAnswer());
//        System.out.println(newQuestionResponse.getChoices());

        Question question = Question.builder()
                .category(categoryService.getCategoryById(newQuestionResponse.getCategoryId()))
                .content(newQuestionResponse.getDescription())
                .isActive(true)
                .choices(new ArrayList<>())
                .build();
        questionService.addQuestion(question);
        for(int i = 0; i < newQuestionResponse.getChoices().size(); i++){
            ChoiceRequest choice = newQuestionResponse.getChoices().get(i);
            Choice currChoice = null;
            if(i == newQuestionResponse.getCorrectAnswer()){
                currChoice = Choice.builder()
                        .description(choice.getDescription())
                        .isCorrect(true)
                        .question(question)
                        .build();
            }else{
                currChoice = Choice.builder()
                        .description(choice.getDescription())
                        .isCorrect(false)
                        .question(question)
                        .build();
            }
            questionService.addChoice(currChoice);
            question.getChoices().add(currChoice);
        }

        return ResponseStatus.builder()
                .success(true)
                .message("Success add a question!")
                .build();
    }

    @GetMapping("/editquestion/{questionId}")
    public EditQuestionResponse addQuestion(@PathVariable int questionId) {
        Question question = questionService.getQuestionById(questionId);
        List<ChoiceDTO> choices = questionService.getChoiceByQuestionId(questionId);
        for(int i = 0; i < choices.size(); i++){
            ChoiceDTO currChoice = choices.get(i);

            if(currChoice.isCorrect()){
                return EditQuestionResponse.builder()
                        .correctAnswer(i+1)
                        .description(question.getContent())
                        .choices(choices)
                        .build();
            }
        }
        return null;
    }

    @PostMapping("/editquestion/{questionId}")
    public ResponseStatus saveEditedQuestion(@PathVariable int questionId, @RequestBody EditQuestionResponse editQuestionRequest) {
        questionService.saveEditedQuestion(questionId, editQuestionRequest);

        return ResponseStatus.builder()
                .success(true)
                .message("Success delete a question!")
                .build();
    }
}
