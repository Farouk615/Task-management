package com.example.interview.dto;

import com.example.interview.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWithoutTeamDto {
    private Long id;
    private String name;
    private Role role;
    private String email;
    private String phoneNumber;
}
