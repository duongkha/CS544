package miu.edu.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.Admin;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface AdminRepository extends CrudRepository<Admin,Long> {
    public List<Admin> findAll();
    public Admin findAdminById(Long id);
    public Admin findAdminByUserId(Long id);
}
