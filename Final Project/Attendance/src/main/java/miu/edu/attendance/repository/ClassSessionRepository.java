package miu.edu.attendance.repository;

import miu.edu.attendance.domain.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSessionRepository extends JpaRepository<ClassSession,Long> {
}
