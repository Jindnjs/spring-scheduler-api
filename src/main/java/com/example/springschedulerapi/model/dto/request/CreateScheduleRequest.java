package com.example.springschedulerapi.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
@Getter
public class CreateScheduleRequest {

        @NotBlank(message = "할일은 필수값 입니다.")
        @Size(max = 200, message = "할일은 최대 200자 입니다.")
        private String task;
        @NotBlank
        private String password;
        @JsonProperty("author_id")
        private Long authorId;
}
