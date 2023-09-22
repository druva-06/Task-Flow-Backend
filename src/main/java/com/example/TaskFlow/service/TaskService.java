package com.example.TaskFlow.service;

import com.example.TaskFlow.DTOs.ReponseDTOs.TaskObjectResponseDTO;
import com.example.TaskFlow.DTOs.ReponseDTOs.TaskResponseDTO;
import com.example.TaskFlow.DTOs.RequestDTOs.DescriptionUpdateRequestDto;
import com.example.TaskFlow.DTOs.RequestDTOs.StatusUpdateRequestDto;
import com.example.TaskFlow.DTOs.RequestDTOs.TaskRequestDTO;
import com.example.TaskFlow.DTOs.RequestDTOs.TitleUpdateRequestDto;
import com.example.TaskFlow.exception.DataUploadException;
import com.example.TaskFlow.exception.InvalidTaskException;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskService {

    TaskResponseDTO addTask(TaskRequestDTO taskRequestDTO) throws Exception;

    TaskResponseDTO getTaskTitleByToken(String token) throws InvalidTaskException;

    TaskResponseDTO getTaskDescriptionByToken(String token) throws InvalidTaskException;

    TaskResponseDTO updateTaskTitleByToken(TitleUpdateRequestDto titleUpdateRequestDto) throws InvalidTaskException;

    TaskResponseDTO updateTaskDescriptionByToken(DescriptionUpdateRequestDto descriptionUpdateRequestDto) throws InvalidTaskException;

    TaskResponseDTO updateTaskStatusByToken(StatusUpdateRequestDto statusUpdateRequestDto) throws InvalidTaskException;

    TaskResponseDTO deleteTaskByToken(String token) throws InvalidTaskException;

    List<TaskObjectResponseDTO> getAllTasks(String emailId) throws Exception;

    TaskResponseDTO generateUniqueToken() throws Exception;
}
