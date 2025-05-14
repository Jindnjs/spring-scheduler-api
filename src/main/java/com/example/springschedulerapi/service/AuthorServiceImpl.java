package com.example.springschedulerapi.service;

import com.example.springschedulerapi.dao.AuthorDao;
import com.example.springschedulerapi.exception.AuthorNotFoundException;
import com.example.springschedulerapi.model.dto.request.AuthorRequestDTO;
import com.example.springschedulerapi.model.dto.request.ScheduleRequestDTO;
import com.example.springschedulerapi.model.dto.response.AuthorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {
        Long id = authorDao.save(dto);
        return authorDao.getAuthorById(id).get();
    }

    @Override
    public void validateAuthor(ScheduleRequestDTO dto) {
        if(authorDao.getAuthorById(dto.getAuthorId()).isEmpty()) {
            throw new AuthorNotFoundException("존재하지 않는 사용자입니다.");
        }
    }
}
