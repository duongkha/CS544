package miu.edu.attendance.service;

import java.util.List;

import miu.edu.attendance.domain.BarcodeRecord;
import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.dto.BarcodeRecordDTO;

public interface BarcodeRecordService {
	public boolean add(BarcodeRecordDTO barcodeDTO);
    public boolean update(BarcodeRecordDTO barcodeDTO);
    public boolean delete(Long id);
    public BarcodeRecord findBarcodeRecordByID( Long id);
    public List<BarcodeRecord> getAllBarcodeRecord();
    public List<AttendanceRecordDTO> findAllAttendanceByStudentIdAndCourse(Long studentId, Long courseId);
}
