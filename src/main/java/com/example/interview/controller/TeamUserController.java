package com.example.interview.controller;

import com.example.interview.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "team-user/")
@RequiredArgsConstructor
public class TeamUserController {

    private final TeamService teamService;
    @PostMapping("assign-user-to-team")
    public ResponseEntity<String> assignUserToTeam(@RequestParam Long userId, @RequestParam Long teamId) {
        boolean success = teamService.assignUserToTeam(userId, teamId);
        if (success) {
            return ResponseEntity.ok("User successfully assigned to team.");
        } else {
            return ResponseEntity.badRequest().body("Failed to assign user to team.");
        }
    }
}
