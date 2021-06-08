package miu.edu.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.Admin;
import miu.edu.attendance.domain.BarcodeRecord;

import java.util.List;

@Repository
public interface BarcodeRecordRepository extends CrudRepository<BarcodeRecord,Long> {
    public List<BarcodeRecord> findAll();
    public BarcodeRecord findBarcodeRecordById(Long id);
}
