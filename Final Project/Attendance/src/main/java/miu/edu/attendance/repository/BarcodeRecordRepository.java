package miu.edu.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.Admin;
import miu.edu.attendance.domain.BarcodeRecord;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface BarcodeRecordRepository extends CrudRepository<BarcodeRecord,Long> {
    public List<BarcodeRecord> findAll();
    public BarcodeRecord findBarcodeRecordById(Long id);
}
