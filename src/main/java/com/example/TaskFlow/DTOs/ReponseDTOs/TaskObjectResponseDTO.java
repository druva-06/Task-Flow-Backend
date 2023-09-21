package com.example.TaskFlow.DTOs.ReponseDTOs;

import com.example.TaskFlow.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskObjectResponseDTO {
    String token;
    String title;
    Status status;
    String description;
}
