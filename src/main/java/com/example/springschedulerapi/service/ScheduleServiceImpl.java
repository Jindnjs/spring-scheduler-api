package com.example.springschedulerapi.service;

import com.example.springschedulerapi.dao.ScheduleDao;
import com.example.springschedulerapi.dto.ScheduleRequestDTO;
import com.example.springschedulerapi.dto.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public ScheduleResponseDTO searchSchedule(Long id) {
        return scheduleDao.getScheduleById(id);
    }

    @Override
    public boolean checkPassword(Long id, ScheduleRequestDTO dto) {
        if (dto.getPassword().equals(scheduleDao.getPasswordById(id))){
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO dto) {
        if (dto.getAuthor() != null) {
            scheduleDao.updateAuthor(id, dto.getAuthor());
        }
        if (dto.getTask() != null) {
            scheduleDao.updateTask(id, dto.getTask());
        }
        return scheduleDao.getScheduleById(id);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleDao.deleteSchedule(id);
    }
}
