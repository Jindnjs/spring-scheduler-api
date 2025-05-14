package com.example.springschedulerapi.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleRequest {

    private String task;
    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;
    @JsonProperty("author_id")
    private Long authorId;
}
