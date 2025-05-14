package com.example.springschedulerapi.service;

import com.example.springschedulerapi.model.dto.request.AuthorRequestDTO;
import com.example.springschedulerapi.model.dto.response.AuthorResponseDTO;

public interface AuthorService {

    /**
     * 작성자를 생성하는 메소드
     * 작성자의 유효성을 검증하고 결과반환
     * @param dto 생성할 작성자의 정보를 담은 dto
     * @return 생성된 작성자의 dto {@link AuthorResponseDTO}
     */
    AuthorResponseDTO createAuthor(AuthorRequestDTO dto);


}
