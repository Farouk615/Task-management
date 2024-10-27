package com.example.interview.mapper;
import com.example.interview.dto.UserWithoutTeamDto;
import com.example.interview.entity.User;

public class UserMapper {
    public static UserWithoutTeamDto toDTO(User user){
        return UserWithoutTeamDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
