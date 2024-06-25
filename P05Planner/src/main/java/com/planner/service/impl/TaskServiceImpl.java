package com.planner.service.impl;

import com.planner.model.dtos.TaskAddDTO;
import com.planner.model.entity.Task;
import com.planner.model.entity.User;
import com.planner.repo.TaskRepository;
import com.planner.service.PriorityService;
import com.planner.service.TaskService;
import com.planner.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final PriorityService priorityService;

    public TaskServiceImpl(ModelMapper modelMapper, TaskRepository taskRepository, UserService userService, PriorityService priorityService) {
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.priorityService = priorityService;
    }

    @Override
    public void saveTask(TaskAddDTO taskAddDTO) {
        Task task = modelMapper.map(taskAddDTO,Task.class);
        task.setPriority(this.priorityService.findPriorityByName(taskAddDTO.getPriority()));
        this.taskRepository.saveAndFlush(task);
    }

    @Override
    public List<Task> findAllAssignedTasks() {
        return this.taskRepository.findAllByUserUsername(this.userService.findCurrendUser().getUsername());
    }

    @Override
    public List<Task> findAllTasks() {
        return this.taskRepository.findAllByUserIsNull();
    }

    @Override
    public void assignToMe(String taskId) {
        Task task = this.taskRepository.findById(taskId).orElse(null);
        User user = this.userService.findCurrendUser();
        if (task != null && user != null){
            task.setUser(user);
            taskRepository.saveAndFlush(task);
        }
    }

    @Override
    public void returnTask(String taskId) {
        Task task = this.taskRepository.findById(taskId).orElse(null);
        if (task != null){
            task.setUser(null);
            taskRepository.saveAndFlush(task);
        }
    }

    @Override
    public void doneTask(String taskId) {
        Task task = this.taskRepository.findById(taskId).orElse(null);
        if (task != null){
            this.taskRepository.delete(task);
        }
    }
}
