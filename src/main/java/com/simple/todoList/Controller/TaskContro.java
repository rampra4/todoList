package com.simple.todoList.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simple.todoList.Entity.TaskEntity;
import com.simple.todoList.Services.TaskService;

@Controller
public class TaskContro {
    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("taskEntity", new TaskEntity());
        return "AddTask";
}

    @PostMapping("/save")
    public String savetask(@ModelAttribute TaskEntity taskEntity){
        taskService.saveTask(taskEntity);
         return "redirect:/all-task";
    }

    @GetMapping("/all-task")
    public String gettask(Model model){
       List<TaskEntity> taskEntity = taskService.getAllTasks();
        model.addAttribute("tasks" , taskEntity);
        return "all-task";
    }
    @PostMapping("/delete/{id}")
    public String deteteTask(@PathVariable long id){
        taskService.deleteTask(id);
        return "redirect:/all-task";
    }

    @GetMapping("/edit/{id}")
    public String updateTask(@PathVariable long id , Model model){
        TaskEntity task = taskService.gettask(id);
        model.addAttribute("task" , task);
        return "update-task";
    }

    @PostMapping("/updateExecute")
    public String updatedTask(@ModelAttribute TaskEntity taskEntity){
        taskService.saveTask(taskEntity);
        return "redirect:/all-task";
    }
}
