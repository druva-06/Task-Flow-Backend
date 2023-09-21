package com.example.TaskFlow.controller;

import com.example.TaskFlow.DTOs.ReponseDTOs.UserSuccessResponseDTO;
import com.example.TaskFlow.DTOs.RequestDTOs.UserRequestDTO;
import com.example.TaskFlow.entity.User;
import com.example.TaskFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDTO userRequestDTO){
        try{
            return new ResponseEntity(userService.addUser(userRequestDTO),HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/validate")
    public  ResponseEntity validateUser(@RequestParam String emailId, @RequestParam String password){
        try{
            return new ResponseEntity(userService.validateUser(emailId,password),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
