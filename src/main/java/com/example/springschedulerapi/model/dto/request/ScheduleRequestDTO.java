package com.example.springschedulerapi.model.dto.request;

import lombok.Getter;

@Getter
public class ScheduleRequestDTO {
    private String task;
    private String author;
    private String password;
}
