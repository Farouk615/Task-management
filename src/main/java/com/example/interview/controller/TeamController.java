package com.example.interview.controller;

import com.example.interview.dto.TeamDto;
import com.example.interview.dto.request.CreateTeamRequestDto;
import com.example.interview.entity.Team;
import com.example.interview.mapper.TeamMapper;
import com.example.interview.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(path = "team/")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("create")
    public ResponseEntity<String> createTeam(@Valid @RequestBody CreateTeamRequestDto createTeamRequestDto) {
        try {
            return new ResponseEntity<>(teamService.createTeam(createTeamRequestDto), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Team creation failed");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getById(@PathVariable Long id) {
        Optional<Team> team = teamService.getTeamById(id);
        return team.map(value -> new ResponseEntity<>(TeamMapper.toDTO(value), HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
