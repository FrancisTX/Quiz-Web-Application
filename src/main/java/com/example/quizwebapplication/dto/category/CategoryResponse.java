package com.example.quizwebapplication.dto.category;

import com.example.quizwebapplication.dto.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class CategoryResponse {
    ResponseStatus status;
    List<CategoryDTO> categories;
}
