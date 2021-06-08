package miu.edu.attendance.service;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.ClassSessionDTO;
import miu.edu.attendance.dto.TimeSlotDTO;

import java.util.List;

public interface ClassSessionService {

    public boolean addClassSession(ClassSessionDTO classSessionDTO);
    public boolean updateClassSession(ClassSessionDTO classSessionDTO);
    public boolean deleteClassSession(Long id);
    public ClassSession findTClassSessionByID(Long id);
    public List<ClassSession> getAllClassSessions();

}
