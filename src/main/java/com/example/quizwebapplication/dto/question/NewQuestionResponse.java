package com.example.quizwebapplication.dto.question;

import com.example.quizwebapplication.dto.choice.ChoiceRequest;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NewQuestionResponse {
    @NotNull
    private Integer categoryId;
    @NotBlank
    private String description;
    @NotEmpty
    private List<ChoiceRequest> choices;
    @NotEmpty
    private Integer correctAnswer;
}

