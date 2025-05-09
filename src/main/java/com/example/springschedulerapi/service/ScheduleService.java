package com.example.springschedulerapi.service;

import com.example.springschedulerapi.dto.ScheduleRequestDTO;
import com.example.springschedulerapi.dto.ScheduleResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    //
    ScheduleResponseDTO saveSchedule(ScheduleRequestDTO dto);

    List<ScheduleResponseDTO> searchSchedules(LocalDate start, LocalDate end, String author);

    ScheduleResponseDTO searchSchedule(Long id);

    boolean checkPassword(Long id, ScheduleRequestDTO dto) ;

    ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO dto);

    void deleteSchedule(Long id);
}
