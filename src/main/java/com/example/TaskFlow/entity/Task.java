package com.example.TaskFlow.entity;

import com.example.TaskFlow.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Task {
    @Id
    private String token;
    private String title;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private String description;
}
