package com.example.springschedulerapi.controller;

import com.example.springschedulerapi.dto.ScheduleResponseDTO;
import com.example.springschedulerapi.dto.ScheduleRequestDTO;
import com.example.springschedulerapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 할일 추가 API
     * @return {@link ResponseEntity<ScheduleResponseDTO>}
     *
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> createSchedule(
            @RequestBody ScheduleRequestDTO dto
    ){
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }
}
