package com.example.TaskFlow.DTOs.RequestDTOs;

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
public class StatusUpdateRequestDto {
    String token;
    Status status;
}
