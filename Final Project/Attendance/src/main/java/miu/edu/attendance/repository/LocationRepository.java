package miu.edu.attendance.repository;

import miu.edu.attendance.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
