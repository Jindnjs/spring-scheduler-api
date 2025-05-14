package com.example.springschedulerapi.service;

import com.example.springschedulerapi.dao.AuthorDao;
import com.example.springschedulerapi.model.dto.request.AuthorRequestDTO;
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
}
