package com.example.TaskFlow.controller;

import com.example.TaskFlow.DTOs.RequestDTOs.DescriptionUpdateRequestDto;
import com.example.TaskFlow.DTOs.RequestDTOs.StatusUpdateRequestDto;
import com.example.TaskFlow.DTOs.RequestDTOs.TaskRequestDTO;
import com.example.TaskFlow.DTOs.RequestDTOs.TitleUpdateRequestDto;
import com.example.TaskFlow.exception.DataUploadException;
import com.example.TaskFlow.exception.InvalidTaskException;
import com.example.TaskFlow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity addTask(@RequestBody TaskRequestDTO taskRequestDTO){
        try{
            return new ResponseEntity(taskService.addTask(taskRequestDTO),HttpStatus.CREATED);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/allTasks")
    public ResponseEntity getAllTasks(@RequestParam String emailId){
        try{
            return new ResponseEntity(taskService.getAllTasks(emailId),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getTitle")
    public ResponseEntity getTaskTitleByToken(@RequestParam String token){
        try{
            return new ResponseEntity(taskService.getTaskTitleByToken(token),HttpStatus.ACCEPTED);
        }
        catch (InvalidTaskException invalidTaskException){
            return new ResponseEntity(invalidTaskException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getDescription")
    public ResponseEntity getTaskDescriptionByToken(@RequestParam String token){
        try{
            return new ResponseEntity(taskService.getTaskDescriptionByToken(token),HttpStatus.ACCEPTED);
        }
        catch (InvalidTaskException invalidTaskException){
            return new ResponseEntity(invalidTaskException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateTitle")
    public ResponseEntity updateTaskTitleByToken(@RequestBody TitleUpdateRequestDto titleUpdateRequestDto){
        try{
            return new ResponseEntity(taskService.updateTaskTitleByToken(titleUpdateRequestDto),HttpStatus.ACCEPTED);
        }
        catch (InvalidTaskException invalidTaskException){
            return new ResponseEntity(invalidTaskException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateDescription")
    public ResponseEntity updateTaskDescriptionByToken(@RequestBody DescriptionUpdateRequestDto descriptionUpdateRequestDto){
        try{
            return new ResponseEntity(taskService.updateTaskDescriptionByToken(descriptionUpdateRequestDto),HttpStatus.ACCEPTED);
        }
        catch (InvalidTaskException invalidTaskException){
            return new ResponseEntity(invalidTaskException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateStatus")
    public ResponseEntity updateTaskStatusByToken(@RequestBody StatusUpdateRequestDto statusUpdateRequestDto){
        try{
            return new ResponseEntity(taskService.updateTaskStatusByToken(statusUpdateRequestDto),HttpStatus.ACCEPTED);
        }
        catch (InvalidTaskException invalidTaskException){
            return new ResponseEntity(invalidTaskException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteTask")
    public ResponseEntity deleteTaskByToken(@RequestParam String token){
        try{
            return new ResponseEntity(taskService.deleteTaskByToken(token),HttpStatus.ACCEPTED);
        }
        catch (InvalidTaskException invalidTaskException){
            return new ResponseEntity(invalidTaskException.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}