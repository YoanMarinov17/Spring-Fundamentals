package com.planner.model.dtos;

import com.planner.model.entity.Priority;
import com.planner.model.enums.PriorityName;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class TaskAddDTO {

    @NotNull
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @NotNull(message = "Due Date cannot be empty!")
    @Future(message = "Due Date must be in future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull(message = "You must select a priority!")
    private PriorityName priority;
}
