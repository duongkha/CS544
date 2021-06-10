package miu.edu.attendance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.BarcodeRecord;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface BarcodeRecordRepository extends PagingAndSortingRepository<BarcodeRecord,Long> {
    public Page<BarcodeRecord> findAll(Pageable pageable);
    public BarcodeRecord findBarcodeRecordById(Long id);
}
