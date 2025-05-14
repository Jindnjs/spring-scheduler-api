package com.example.springschedulerapi.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateAuthorRequest {
    @NotBlank(message = "이름은 필수값 입니다.")
    private String name;
    @Email(message = "이메일 형식으로 입력하세요")
    private String email;
}
