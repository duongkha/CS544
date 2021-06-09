package miu.edu.attendance.repository;

import miu.edu.attendance.domain.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ClassSessionRepository extends JpaRepository<ClassSession,Long> {
}
