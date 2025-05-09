package com.example.springschedulerapi.controller;

import com.example.springschedulerapi.dto.ScheduleRequestDTO;
import com.example.springschedulerapi.dto.ScheduleResponseDTO;
import com.example.springschedulerapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }

    /**
     * 전체 일정 조회 API
     * @param start 조회 시작 날짜 (옵션, "yyyy-MM-dd" 형식)
     * @param end 조회 종료 날짜 (옵션, "yyyy-MM-dd" 형식)
     * @param author 조회할 일정의 작성자 (옵션)
     * @return 조건에 맞는 일정 리스트 {@link List<ScheduleResponseDTO>}
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> getAllSchedules(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(required = false) String author
    ) {
        //날짜 기본값 처리
        if(start == null)
            start = LocalDate.now().minusMonths(1);
        if(end == null)
            end = LocalDate.now();
        return new ResponseEntity<>(scheduleService.searchSchedules(start, end, author),HttpStatus.OK);
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
}
