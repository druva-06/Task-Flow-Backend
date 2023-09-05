package com.example.TaskFlow.DTOs.RequestDTOs;

import com.example.TaskFlow.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRequestDTO {
    String token;
    String title;
    Status status;
    String description;
}
