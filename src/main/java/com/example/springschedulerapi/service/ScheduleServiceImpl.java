package com.example.springschedulerapi.service;

import com.example.springschedulerapi.dao.ScheduleDao;
import com.example.springschedulerapi.dto.ScheduleResponseDTO;
import com.example.springschedulerapi.dto.ScheduleRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;

    @Override
    public ScheduleResponseDTO saveSchedule(ScheduleRequestDTO dto) {
        Long id = scheduleDao.insertSchedule(dto);
        return scheduleDao.getScheduleById(id);
    }
}
