package com.example.TaskFlow.service;

import com.example.TaskFlow.DTOs.ReponseDTOs.UserSuccessResponseDTO;
import com.example.TaskFlow.DTOs.RequestDTOs.UserRequestDTO;
import com.example.TaskFlow.entity.User;

public interface UserService {

    UserSuccessResponseDTO addUser(UserRequestDTO userRequestDTO) throws Exception;

    UserSuccessResponseDTO validateUser(String emailId, String password) throws Exception;

}
