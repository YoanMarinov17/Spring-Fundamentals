package com.planner.service;

import com.planner.model.dtos.TaskAddDTO;
import com.planner.model.entity.Task;
import java.util.List;

public interface TaskService {
    void saveTask(TaskAddDTO taskAddDTO);

    List<Task> findAllAssignedTasks();

    List<Task> findAllTasks();

    void assignToMe(String taskId);

    void returnTask(String taskId);

    void doneTask(String taskId);
}
