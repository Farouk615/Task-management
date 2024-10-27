package com.example.interview.respository;

import com.example.interview.entity.Project;
import com.example.interview.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}

