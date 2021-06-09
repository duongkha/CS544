package miu.edu.attendance.repository;

import org.springframework.data.repository.CrudRepository;

import miu.edu.attendance.domain.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RoleRepository extends CrudRepository<Role,Long> {
    public List<Role> findAll();
    public Role findRoleById(Long id);
    public Role findRoleByName(String name);
    public List<Role> findRolesByIdIn(List<Long> Ids);
}
