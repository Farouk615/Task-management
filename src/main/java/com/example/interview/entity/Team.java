package com.example.interview.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "max_members")
    private Integer maxMembers;

    @Transient
    private int members;

    @Getter
    @ManyToMany(mappedBy = "teams")
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public int getMembers(){
        return users!=null ? users.size() : 0;
    }
}
