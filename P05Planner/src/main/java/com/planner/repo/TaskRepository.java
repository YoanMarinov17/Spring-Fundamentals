package com.planner.repo;

import com.planner.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    List<Task> findAllByUserUsername(String username);

    List<Task> findAllByUserIsNull();
}