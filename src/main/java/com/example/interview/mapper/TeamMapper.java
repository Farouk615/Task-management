package com.example.interview.mapper;

import com.example.interview.dto.TeamDto;
import com.example.interview.dto.UserWithoutTeamDto;
import com.example.interview.entity.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {
    public static TeamDto toDTO(Team team){
        return TeamDto
                .builder()
                .name(team.getName())
                .maxMembers(team.getMaxMembers())
                .users(team.getUsers().stream().map(UserMapper::toDTO).collect(Collectors.toList()))
                .members(team.getMembers())
                .build();
    }
    public static Team toEntity(TeamDto teamDto){
        return Team
                .builder()
                .maxMembers(teamDto.getMaxMembers())
                .name(teamDto.getName())
                .build();
    }
}
