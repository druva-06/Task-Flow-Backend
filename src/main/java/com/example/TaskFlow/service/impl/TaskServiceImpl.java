package com.example.TaskFlow.service.impl;

import com.example.TaskFlow.DTOs.ReponseDTOs.TaskObjectResponseDTO;
import com.example.TaskFlow.DTOs.ReponseDTOs.TaskResponseDTO;
import com.example.TaskFlow.DTOs.RequestDTOs.DescriptionUpdateRequestDto;
import com.example.TaskFlow.DTOs.RequestDTOs.StatusUpdateRequestDto;
import com.example.TaskFlow.DTOs.RequestDTOs.TaskRequestDTO;
import com.example.TaskFlow.DTOs.RequestDTOs.TitleUpdateRequestDto;
import com.example.TaskFlow.entity.Task;
import com.example.TaskFlow.entity.User;
import com.example.TaskFlow.enums.Status;
import com.example.TaskFlow.exception.DataUploadException;
import com.example.TaskFlow.exception.InvalidTaskException;
import com.example.TaskFlow.repository.TaskRepository;
import com.example.TaskFlow.repository.UserRepository;
import com.example.TaskFlow.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public TaskResponseDTO addTask(TaskRequestDTO taskRequestDTO) throws Exception {

        User user = userRepository.findByEmailId(taskRequestDTO.getEmailId());

        if(user == null){
            return new TaskResponseDTO("Invalid EmailId!");
        }

        Task task = new Task();
        task.setToken(taskRequestDTO.getToken());
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setStatus(taskRequestDTO.getStatus());

        user.getTaskList().add(task);
        task.setUser(user);

        userRepository.save(user);

        System.out.println(user.getTaskList());

        String message = task.getTitle() + " Task is Created Successfully!";
        return new TaskResponseDTO(message);
    }

    @Override
    public TaskResponseDTO getTaskTitleByToken(String token) throws InvalidTaskException {
        if(taskRepository.existsById(token)){
            return new TaskResponseDTO(taskRepository.findById(token).get().getTitle());
        }
        else{
            throw new InvalidTaskException("Invalid Id!");
        }
    }

    @Override
    public TaskResponseDTO getTaskDescriptionByToken(String token) throws InvalidTaskException {
        if(taskRepository.existsById(token)){
            return new TaskResponseDTO(taskRepository.findById(token).get().getDescription());
        }
        else{
            throw new InvalidTaskException("Invalid Id!");
        }
    }

    @Override
    public TaskResponseDTO updateTaskTitleByToken(TitleUpdateRequestDto titleUpdateRequestDto) throws InvalidTaskException {
        String token = titleUpdateRequestDto.getToken();
        String title = titleUpdateRequestDto.getTitle();
        if(taskRepository.existsById(token)){
            Task task = taskRepository.findById(token).get();
            task.setTitle(title);
            taskRepository.save(task);

            return new TaskResponseDTO("Title updated successfully!");
        }
        else{
            throw new InvalidTaskException("Invalid Id!");
        }
    }

    @Override
    public TaskResponseDTO updateTaskDescriptionByToken(DescriptionUpdateRequestDto descriptionUpdateRequestDto) throws InvalidTaskException {
        String token = descriptionUpdateRequestDto.getToken();
        String description = descriptionUpdateRequestDto.getDescription();
        if(taskRepository.existsById(token)){
            Task task = taskRepository.findById(token).get();
            task.setDescription(description);
            taskRepository.save(task);

            return new TaskResponseDTO("Description updated successfully!");
        }
        else{
            throw new InvalidTaskException("Invalid Id!");
        }
    }

    @Override
    public TaskResponseDTO updateTaskStatusByToken(StatusUpdateRequestDto statusUpdateRequestDto) throws InvalidTaskException {
        String token = statusUpdateRequestDto.getToken();
        Status status = statusUpdateRequestDto.getStatus();
        if(taskRepository.existsById(token)){
            Task task = taskRepository.findById(token).get();
            task.setStatus(status);
            taskRepository.save(task);

            return new TaskResponseDTO("Status updated successfully!");
        }
        else{
            throw new InvalidTaskException("Invalid Id!");
        }
    }

    @Override
    public TaskResponseDTO deleteTaskByToken(String token) throws InvalidTaskException {
        if(taskRepository.existsById(token)){
            taskRepository.deleteById(token);
            return new TaskResponseDTO("Task Deleted Successfully!");
        }
        else{
            throw new InvalidTaskException("Invalid Task");
        }
    }

    @Override
    public List<TaskObjectResponseDTO> getAllTasks(String emailId) throws Exception {
        int user_id = userRepository.findByEmailId(emailId).getId();

        List<Task> taskList = taskRepository.findByUserId(user_id);
        List<TaskObjectResponseDTO> taskObjectResponseDTOS = new ArrayList<>();
        for(Task task: taskList){
            TaskObjectResponseDTO taskObjectResponseDTO = new TaskObjectResponseDTO(
                    task.getToken(),task.getTitle(), task.getStatus(),task.getDescription());
            taskObjectResponseDTOS.add(taskObjectResponseDTO);
        }
        return taskObjectResponseDTOS;
    }

    @Override
    public TaskResponseDTO generateUniqueToken() throws Exception {

        Random random = new Random();
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String token = "";

        while(token.equals("") || taskRepository.existsById(token)){
            token = "";
            for(int i = 0; i < 15; i++){
                if(i != 0){
                    token += str.charAt(random.nextInt(str.length()));
                }
                else{
                    token += str.charAt(random.nextInt(str.length() - 10));
                }
            }
        }

        return new TaskResponseDTO(token);
    }
}
