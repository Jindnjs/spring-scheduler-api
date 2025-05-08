package com.example.springschedulerapi.dao;

import com.example.springschedulerapi.dto.ScheduleRequestDTO;
import com.example.springschedulerapi.dto.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ScheduleDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * INSERT
     * @param
     */
    public Long insertSchedule(ScheduleRequestDTO dto) {
        String query = "INSERT INTO schedule (task, author, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, dto.getTask(), dto.getAuthor(), dto.getPassword());
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        log.info("insert schedule id : {}", id);
        return id;
    }

    public ScheduleResponseDTO getScheduleById(Long id) {
        String query = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(ScheduleResponseDTO.class), id);
    }

}
