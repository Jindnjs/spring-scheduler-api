package com.example.springschedulerapi.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ScheduleRequestDTO {
    private String task;
    private String password;
    @JsonProperty("author_id")
    private Long authorId;
}
