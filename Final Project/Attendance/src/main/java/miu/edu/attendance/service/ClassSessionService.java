package miu.edu.attendance.service;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.dto.ClassSessionRequestDTO;
import miu.edu.attendance.dto.ClassSessionResponseDTO;

import java.util.List;

public interface ClassSessionService {

    public boolean addClassSession(ClassSessionRequestDTO classSessionRequestDTO);
    public boolean updateClassSession(ClassSessionRequestDTO classSessionRequestDTO);
    public boolean deleteClassSession(Long id);
    public ClassSessionResponseDTO findTClassSessionByID(Long id);
    public List<ClassSessionResponseDTO> getAllClassSessions();

}
