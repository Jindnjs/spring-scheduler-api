package com.example.springschedulerapi.controller;

import com.example.springschedulerapi.model.dto.request.ScheduleRequestDTO;
import com.example.springschedulerapi.model.dto.response.ScheduleResponseDTO;
import com.example.springschedulerapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 추가 API
     * @param dto 일정 생성에 필요한 데이터
     * @return 생성된 일정이 담긴 데이터 {@link ResponseEntity<ScheduleResponseDTO>}
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> createSchedule(
            @RequestBody ScheduleRequestDTO dto
    ){
        return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.CREATED);
    }

    /**
     * 전체 일정 조회 API - 검색조건
     * @param start 조회 시작 날짜 (옵션, "yyyy-MM-dd" 형식)
     * @param end 조회 종료 날짜 (옵션, "yyyy-MM-dd" 형식)
     * @param authorId 조회할 일정의 작성자 아이디(옵션)
     * @return 조건에 맞는 일정 리스트 {@link List<ScheduleResponseDTO>}
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> getSchedules(
            @RequestParam(required = false) LocalDate start,
            @RequestParam(required = false) LocalDate end,
            @RequestParam(name = "author_id", required = false) Long authorId
    ) {
        return new ResponseEntity<>(scheduleService.searchSchedules(start, end, authorId),HttpStatus.OK);
    }

    /**
     * 선택 일정 조회 API
     * @param id 조회할 일정의 id
     * @return 조회된 일정의 DTO {@link ScheduleResponseDTO}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> getScheduleById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(scheduleService.searchSchedule(id), HttpStatus.OK);
    }

    /**
     * 페이지 검색 API
     * @param
     * @return 조건에 맞는 일정 페이지 {@link Page<ScheduleResponseDTO>}
     */
    @GetMapping("/page")
    public ResponseEntity<Page<ScheduleResponseDTO>> getSchedulePageable(
            Pageable page
    ) {
        return new ResponseEntity<>(scheduleService.getSchedulePages(page),HttpStatus.OK);
    }


    /**
     * 선택 일정 수정 API
     * @param id 수정할 일정의 id
     * @param dto 수정 요청 Dto
     * @return 수정된 일정의 DTO {@link ScheduleResponseDTO}
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSchedule(
        @PathVariable Long id,
        @RequestBody ScheduleRequestDTO dto
    ){
        if(dto.getAuthorId() == null && dto.getTask() == null)
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("수정할내용이 없습니다.");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleService.updateSchedule(id, dto));
    }

    /**
     * 선택 일정 삭제 API
     * @param id 삭제할 일정의 id
     * @param dto 비밀번호를 전달하는 DTO
     * @return 응답 메시지
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDTO dto
    ){
        scheduleService.deleteSchedule(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
