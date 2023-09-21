package com.example.TaskFlow.service.impl;

import com.example.TaskFlow.DTOs.ReponseDTOs.UserSuccessResponseDTO;
import com.example.TaskFlow.DTOs.RequestDTOs.UserRequestDTO;
import com.example.TaskFlow.entity.User;
import com.example.TaskFlow.repository.UserRepository;
import com.example.TaskFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserSuccessResponseDTO addUser(UserRequestDTO userRequestDTO) throws Exception {
        try {
            String emailId = userRequestDTO.getEmailId();
            String password = userRequestDTO.getPassword();

            if(userRepository.findByEmailId(emailId)!=null){
                return new UserSuccessResponseDTO(false);
            }

            User user = new User();

            user.setEmailId(emailId);
            user.setPassword(password);
            System.out.println(user.getTaskList());
            userRepository.save(user);
            return new UserSuccessResponseDTO(true);
        }
        catch (Exception e){
            return new UserSuccessResponseDTO(false);
        }
    }

    @Override
    public UserSuccessResponseDTO validateUser(String emailId, String password) throws Exception {
        try {
            User user = userRepository.findByEmailId(emailId);
            if(user == null) return new UserSuccessResponseDTO(false);
            if(!user.getPassword().equals(password)) return new UserSuccessResponseDTO(false);
            return new UserSuccessResponseDTO(true);
        }
        catch (Exception e){
            return new UserSuccessResponseDTO(false);
        }
    }
}
