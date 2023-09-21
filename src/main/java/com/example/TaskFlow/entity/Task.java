package com.example.TaskFlow.entity;

import com.example.TaskFlow.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Task {
    @Id
    String token;
    String title;
    @Enumerated(value = EnumType.STRING)
    Status status;
    String description;
    @ManyToOne
    @JoinColumn
    User user;
}
