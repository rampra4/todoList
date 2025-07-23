package com.simple.todoList.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple.todoList.Entity.TaskEntity;

public interface Taskrepo extends JpaRepository<TaskEntity , Long>{
    
    public List<TaskEntity> findTaskEntityBystatus(String status);

    public void deleteBytaskdetail(String taskdetail);

    
}
