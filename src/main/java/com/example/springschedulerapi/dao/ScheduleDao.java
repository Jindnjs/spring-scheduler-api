package com.example.springschedulerapi.dao;

import com.example.springschedulerapi.model.dto.request.CreateScheduleRequest;
import com.example.springschedulerapi.model.dto.response.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * INSERT
     * @param dto 생성할 일정의 정보를 담은 dto
     * @return 생성된 일정의 Id값
     */
    public Long insertSchedule(CreateScheduleRequest dto) {
        String query = "INSERT INTO schedule (task, password, author_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(query, dto.getTask(), dto.getPassword(), dto.getAuthorId());
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        return id;
    }

    /**
     * SELECT 단건 일정 조회
     * @param id 조회할 일정의 id
     * @return 조회된 일정 DTO {@link ScheduleResponseDTO}
     */
    public Optional<ScheduleResponseDTO> getScheduleById(Long id) {
        String query = "SELECT * FROM schedule WHERE id = ?";
        try{
            ScheduleResponseDTO a = jdbcTemplate.queryForObject
                    (query, new BeanPropertyRowMapper<>(ScheduleResponseDTO.class), id);
            return Optional.of(a);
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    /**
     * 일정 목록 조회 - 전체 목록
     * @return 조회된 일정 DTO 리스트 {@link List<ScheduleResponseDTO>}
     */
    public List<ScheduleResponseDTO> getAllSchedule() {
        String query = "SELECT * FROM schedule ORDER BY updated_at DESC";
        return jdbcTemplate.query(
                query,
                new BeanPropertyRowMapper<>(ScheduleResponseDTO.class)
        );
    }

    /**
     * 일정 목록 조회 - 날짜 기준
     * @param start 시작 날짜
     * @param end 종료 날짜
     * @return 조회된 일정 DTO 리스트 {@link List<ScheduleResponseDTO>}
     */
    public List<ScheduleResponseDTO> getSchedulesByDay(LocalDate start, LocalDate end) {
        String query = "SELECT * FROM schedule WHERE updated_at >= ? AND updated_at <= ? ORDER BY updated_at DESC";
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
     * @param authorId 작성자Id
     * @return 조회된 일정 DTO 리스트 {@link List<ScheduleResponseDTO>}
     */
    public List<ScheduleResponseDTO> getSchedulesByDayAndAuthor(LocalDate start, LocalDate end, Long authorId) {
        String query = "SELECT * FROM schedule WHERE updated_at >= ? AND updated_at <= ? AND author_id=? ORDER BY updated_at DESC";
        return jdbcTemplate.query(
                query,
                new BeanPropertyRowMapper<>(ScheduleResponseDTO.class),
                start, end, authorId
        );
    }

    /**
     * 일정 목록 조회 - 날짜 & 작성자명 기준
     * @param authorId 작성자명
     * @return 조회된 일정 DTO 리스트 {@link List<ScheduleResponseDTO>}
     */
    public List<ScheduleResponseDTO> getScheduleByAuthor(Long authorId) {
        String query = "SELECT * FROM schedule WHERE  author_id = ? ORDER BY updated_at DESC";
        return jdbcTemplate.query(
                query,
                new BeanPropertyRowMapper<>(ScheduleResponseDTO.class),
                authorId
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

    public void updateAuthor(Long id, Long authorId) {
        String query = "UPDATE schedule SET author_id = ? WHERE id = ?";
        jdbcTemplate.update(query, authorId, id);
    }
    public void updateTask(Long id, String task) {
        String query = "UPDATE schedule SET task = ? WHERE id = ?";
        jdbcTemplate.update(query, task, id);

    }

    public void deleteSchedule(Long id) {
        String query = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    /**
     * 전체 일정 갯수 반환
     * @return 전체 일정 수
     */
    public int getTotalCount(){
        String query = "SELECT COUNT(*) FROM schedule";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    /**
     * 전체 일정 조회 - 페이징
     * @param
     * @return 페이지에 맞는 일정 리스트 {@link}
     */
    public List<ScheduleResponseDTO> getSchedulePage(Pageable pageable) {
        String query = "SELECT * FROM schedule LIMIT ? OFFSET ?";
        return jdbcTemplate.query(
                query, new BeanPropertyRowMapper<>(ScheduleResponseDTO.class),
                pageable.getPageSize(), pageable.getOffset()
        );
    }
}
