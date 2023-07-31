package com.example.quizwebapplication.dto.question;

import com.example.quizwebapplication.dto.choice.ChoiceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EditQuestionResponse {

    private String description;

    private List<ChoiceDTO> choices;

    private Integer correctAnswer;
}