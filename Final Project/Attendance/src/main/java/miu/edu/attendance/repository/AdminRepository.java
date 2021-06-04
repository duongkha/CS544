package miu.edu.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.Admin;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {
    public List<Admin> findAll();
    public Admin findAdminById(Long id);
}
