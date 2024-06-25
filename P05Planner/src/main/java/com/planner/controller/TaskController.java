package com.planner.controller;

import com.planner.model.dtos.TaskAddDTO;
import com.planner.model.entity.Task;
import com.planner.model.enums.PriorityName;
import com.planner.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/home")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ModelAndView home(ModelAndView model){
        List<Task> allAssignedTasks = this.taskService.findAllAssignedTasks();
        List<Task> allTasks = this.taskService.findAllTasks();
        model.addObject("allAssignedTasks",allAssignedTasks);
        model.addObject("allTasks",allTasks);
        model.setViewName("home");
        return model;
    }


    @ModelAttribute(name = "taskAddDTO")
    public TaskAddDTO taskAddForm(){
        return new TaskAddDTO();
    }

    @GetMapping("/addTask")
    public ModelAndView addTask(ModelAndView model){
        model.addObject("prioritiesNameValues", PriorityName.values());
        model.setViewName("task-add");
        return model;
    }

    @PostMapping("/addTask")
    public ModelAndView addTask(ModelAndView model,
                                 @Valid TaskAddDTO taskAddDTO,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("prioritiesNameValues", PriorityName.values());
            model.setViewName("task-add");
            return model;
        }
        this.taskService.saveTask(taskAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/assignToMe/{taskId}")
    public ModelAndView assignToMe(@PathVariable String taskId, ModelAndView model){
        this.taskService.assignToMe(taskId);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/returnTask/{taskId}")
    public ModelAndView returnTask(@PathVariable String taskId, ModelAndView model){
        this.taskService.returnTask(taskId);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/doneTask/{taskId}")
    public ModelAndView doneTask(@PathVariable String taskId, ModelAndView model){
        this.taskService.doneTask(taskId);
        model.setViewName("redirect:/home");
        return model;
    }
}
