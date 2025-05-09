package com.example.springschedulerapi.dao;

import com.example.springschedulerapi.dto.ScheduleRequestDTO;
import com.example.springschedulerapi.dto.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * INSERT
     * @param dto 생성할 일정의 정보를 담은 dto
     * @return 생성된 일정의 Id값
     */
    public Long insertSchedule(ScheduleRequestDTO dto) {
        String query = "INSERT INTO schedule (task, author, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, dto.getTask(), dto.getAuthor(), dto.getPassword());
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return id;
    }

    /**
     * SELECT 단건 일정 조회
     * @param id 조회할 일정의 id
     * @return 조회된 일정 DTO {@link ScheduleResponseDTO}
     */
    public ScheduleResponseDTO getScheduleById(Long id) {
        String query = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(ScheduleResponseDTO.class), id);
    }

    /**
     * 일정 목록 조회 - 날짜 기준
     * @param start 시작 날짜
     * @param end 종료 날짜
     * @return 조회된 일정 DTO 리스트 {@link List<ScheduleResponseDTO>}
     */
    public List<ScheduleResponseDTO> getSchedulesByDay(LocalDateTime start, LocalDateTime end) {
        String query = "SELECT * FROM schedule WHERE updated_at >= ? AND updated_at < ? ORDER BY updated_at DESC";
        return jdbcTemplate.query(
                query,
                new BeanPropertyRowMapper<>(ScheduleResponseDTO.class),
                start, end
        );
    }

    /**
     * 일정 목록 조회 - 날짜 & 작성자명 기준
     * @param start 시작 날짜
     * @param end 종료 날짜
     * @param author 작성자명
     * @return 조회된 일정 DTO 리스트 {@link List<ScheduleResponseDTO>}
     */
    public List<ScheduleResponseDTO> getSchedulesByDayAndAuthor(LocalDateTime start, LocalDateTime end, String author) {
        String query = "SELECT * FROM schedule WHERE updated_at >= ? AND updated_at < ? AND author like ? ORDER BY updated_at DESC";
        return jdbcTemplate.query(
                query,
                new BeanPropertyRowMapper<>(ScheduleResponseDTO.class),
                start, end, author
        );
    }

    /**
     * 비밀번호 조회 - id
     * @param id 조회할 일정의 id
     * @return id의 password
     */
    public String getPasswordById(Long id) {
        String query = "SELECT password FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(query, String.class, id);
    }

    public void updateAuthor(Long id, String author) {
        String query = "UPDATE schedule SET author = ? WHERE id = ?";
        jdbcTemplate.update(query, author, id);
    }
    public void updateTask(Long id, String task) {
        String query = "UPDATE schedule SET task = ? WHERE id = ?";
        jdbcTemplate.update(query, task, id);

    }

    public void deleteSchedule(Long id) {
        String query = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
