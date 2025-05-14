package com.example.springschedulerapi.dao;

import com.example.springschedulerapi.model.dto.request.AuthorRequestDTO;
import com.example.springschedulerapi.model.dto.response.AuthorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorDao {
    private final JdbcTemplate jdbcTemplate;

    /**
     * INSERT
     * @param dto 생성할 작성자의 정보를 담은 dto
     * @return 생성된 작성자의 id
     */
    public Long save(AuthorRequestDTO dto) {
        String query = "INSERT INTO author (name, email) VALUES (?, ?)";
        jdbcTemplate.update(query, dto.getName(), dto.getEmail());
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return id;
    }

    /**
     * SELECT 단건 일정 조회
     * @param id 조회할 일정의 id
     * @return 조회된 일정 DTO 혹은 Null {@link Optional<AuthorResponseDTO>}
     */
    public Optional<AuthorResponseDTO> getAuthorById(Long id) {
        String query = "SELECT * FROM author WHERE id = ?";
        try{
            AuthorResponseDTO author = jdbcTemplate.queryForObject
                    (query, new BeanPropertyRowMapper<>(AuthorResponseDTO.class), id);
            return Optional.of(author);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
