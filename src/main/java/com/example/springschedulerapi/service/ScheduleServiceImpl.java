package com.example.springschedulerapi.service;

import com.example.springschedulerapi.dao.ScheduleDao;
import com.example.springschedulerapi.dto.ScheduleRequestDTO;
import com.example.springschedulerapi.dto.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;

    @Override
    public ScheduleResponseDTO saveSchedule(ScheduleRequestDTO dto) {
        Long id = scheduleDao.insertSchedule(dto);
        return scheduleDao.getScheduleById(id);
    }

    @Override
    public List<ScheduleResponseDTO> searchSchedules(LocalDate start, LocalDate end, String author) {

        //날짜에 시간추가
        LocalDateTime startTime = start.atStartOfDay();
        LocalDateTime endTime = end.plusDays(1).atStartOfDay();

        List<ScheduleResponseDTO> schedules;

        if(author == null || author.isEmpty())
            return scheduleDao.getSchedulesByDay(startTime, endTime);
        else
            return scheduleDao.getSchedulesByDayAndAuthor(startTime, endTime, author);
    }
}
