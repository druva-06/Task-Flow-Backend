package com.example.TaskFlow.repository;

import com.example.TaskFlow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,String> {
    @Query(value = "select * from task where user_id=:user_id",nativeQuery = true)
    List<Task> findByUserId(int user_id);
}
