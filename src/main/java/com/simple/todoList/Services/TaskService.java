package com.simple.todoList.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.todoList.Entity.TaskEntity;
import com.simple.todoList.Repository.Taskrepo;

@Service
public class TaskService {
    @Autowired
    Taskrepo taskrepo;

    public TaskEntity saveTask(TaskEntity taskEntity){
        return taskrepo.save(taskEntity);
    }

    public List<TaskEntity> getAllTasks(){
        return taskrepo.findAll();
    }

    public void deleteTask(Long id){
        taskrepo.deleteById(id);
        System.out.println("Task Deleted Successfully");
    }

    public List<TaskEntity> getTaskByStatus(String status){
        return taskrepo.findTaskEntityBystatus(status);
    }

    public TaskEntity gettask(long id){
        return taskrepo.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
    }
}
