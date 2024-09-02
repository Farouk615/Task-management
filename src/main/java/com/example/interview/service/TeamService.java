package com.example.interview.service;

import com.example.interview.dto.request.CreateTeamRequestDto;
import com.example.interview.entity.Team;
import com.example.interview.entity.User;
import com.example.interview.respository.TeamRepository;
import com.example.interview.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public String createTeam(CreateTeamRequestDto createTeamRequestDto) {
        var team = Team
                .builder()
                .name(createTeamRequestDto.getName())
                .maxMembers(createTeamRequestDto.getMaxMembers())
                .build();
        teamRepository.save(team);
        return "Team created successfully";
    }

    public boolean assignUserToTeam(Long userId, Long teamId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return false; // User not found
        }
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isEmpty()) {
            return false; // Team not found
        }

        // Get the user and team entities
        User user = optionalUser.get();
        Team team = optionalTeam.get();

        // Update the relationships
        user.getTeams().add(team);
        team.getUsers().add(user);

        // Save the entities back to the repository
        userRepository.save(user);
        teamRepository.save(team);

        return true; // Successfully assigned user to team
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }
}
