package com.example.springschedulerapi.service;

import com.example.springschedulerapi.model.dto.request.ScheduleRequestDTO;
import com.example.springschedulerapi.model.dto.response.ScheduleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    //
    ScheduleResponseDTO createSchedule(ScheduleRequestDTO dto);

    List<ScheduleResponseDTO> searchSchedules(LocalDate start, LocalDate end, Long authorId);

    Page<ScheduleResponseDTO> getSchedulePages(Pageable pageable);

    ScheduleResponseDTO searchSchedule(Long id);

    ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO dto);

    void deleteSchedule(Long id, ScheduleRequestDTO dto);
}
