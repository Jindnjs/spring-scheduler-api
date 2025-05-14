package com.example.springschedulerapi.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponseDTO {
    private Long id;
    private String task;
    private Long authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
