package com.example.springschedulerapi.controller;

import com.example.springschedulerapi.model.dto.request.AuthorRequestDTO;
import com.example.springschedulerapi.model.dto.response.AuthorResponseDTO;
import com.example.springschedulerapi.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    /**
     * 작성자 추가 API
     * @param dto 작성자 생성에 필요한 데이터
     * @return 생성된 작성자 정보가 담긴 데이터 {@link ResponseEntity < AuthorResponseDTO >}
     */
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(
            @RequestBody AuthorRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorService.createAuthor(dto));
    }
}
