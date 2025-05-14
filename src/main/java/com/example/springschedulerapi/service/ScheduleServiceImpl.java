package com.example.springschedulerapi.service;

import com.example.springschedulerapi.dao.ScheduleDao;
import com.example.springschedulerapi.exception.PasswordMissMatchException;
import com.example.springschedulerapi.exception.ScheduleNotFoundException;
import com.example.springschedulerapi.model.dto.request.ScheduleRequestDTO;
import com.example.springschedulerapi.model.dto.response.ScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;
    private final AuthorService authorService;

    @Override
    public ScheduleResponseDTO createSchedule(ScheduleRequestDTO dto) {
        //사용자 검증
        authorService.validateAuthor(dto);
        Long id = scheduleDao.insertSchedule(dto);
        return scheduleDao.getScheduleById(id).get();
    }

    @Override
    public List<ScheduleResponseDTO> searchSchedules(LocalDate start, LocalDate end, Long authorId) {

        boolean dateCondition = (start != null && end != null);
        boolean authorCondition = authorId != null;

        if(!dateCondition && !authorCondition)
            return scheduleDao.getAllSchedule();
        else if(!dateCondition && authorCondition)
            return scheduleDao.getScheduleByAuthor(authorId);
        else if(dateCondition && !authorCondition)
            return scheduleDao.getSchedulesByDay(start, end.plusDays(1));
        else
            return scheduleDao.getSchedulesByDayAndAuthor(start, end.plusDays(1), authorId);
    }

    @Override
    public ScheduleResponseDTO searchSchedule(Long id) {
        Optional<ScheduleResponseDTO> optional = scheduleDao.getScheduleById(id);
        if(optional.isPresent())
            return optional.get();
        else
            throw new ScheduleNotFoundException(id + "은 없는 아이디 입니다.");
    }

    @Transactional
    @Override
    public ScheduleResponseDTO updateSchedule(Long id, ScheduleRequestDTO dto) {

        validatePassword(id,dto);

        if (dto.getAuthorId() != null) {
            authorService.validateAuthor(dto);
            scheduleDao.updateAuthor(id, dto.getAuthorId());
        }
        if (dto.getTask() != null) {
            scheduleDao.updateTask(id, dto.getTask());
        }
        return scheduleDao.getScheduleById(id).get();
    }

    @Override
    public void deleteSchedule(Long id, ScheduleRequestDTO dto) {
        Optional<ScheduleResponseDTO> optional = scheduleDao.getScheduleById(id);
        if(optional.isPresent()) {
            validatePassword(id, dto);
            scheduleDao.deleteSchedule(id);
        }
        else {
            throw new ScheduleNotFoundException(id + "은 없는 아이디 입니다.");
        }

    }

    private void validatePassword(Long id, ScheduleRequestDTO dto) {
        if (!dto.getPassword().equals(scheduleDao.getPasswordById(id))){
            throw new PasswordMissMatchException("비밀번호가 일치하지 않습니다.");
        }
    }
}
