package com.example.springschedulerapi.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDTO {
    private String task;
    private String author;
    private String password;
}
