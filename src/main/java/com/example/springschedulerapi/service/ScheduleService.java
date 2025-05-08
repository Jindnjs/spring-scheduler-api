package com.example.springschedulerapi.service;

import com.example.springschedulerapi.dto.ScheduleResponseDTO;
import com.example.springschedulerapi.dto.ScheduleRequestDTO;

public interface ScheduleService {

    ScheduleResponseDTO saveSchedule(ScheduleRequestDTO dto);
}
