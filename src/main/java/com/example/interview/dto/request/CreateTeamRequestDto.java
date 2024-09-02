package com.example.interview.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTeamRequestDto {
    @NotBlank(message = "The email is required.")
    private String name;

    private Integer maxMembers=5;

}
