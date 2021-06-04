package miu.edu.attendance.service;

import java.util.List;

import miu.edu.attendance.domain.Role;

public interface RoleService {
    List<Role> findAll();
    Role findRoleById(Long id);
    Role findRoleByName(String name);
}
