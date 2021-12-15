package ru.beljankin.scurityboot.dao;


import ru.beljankin.scurityboot.entities.Role;

public interface RoleDAO {
    void save(Role role);
    Role findRoleByString(String roleName);
}
