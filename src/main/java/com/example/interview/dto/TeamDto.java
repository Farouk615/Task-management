package com.example.interview.dto;

import com.example.interview.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {

    private String name;
    private Integer maxMembers;
    private Set<User> users;
    private Integer members;
}
