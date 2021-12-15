package ru.beljankin.scurityboot.services;


import ru.beljankin.scurityboot.entities.Role;

public interface RoleServise {
    void save(Role role);
    Role findRoleByString(String roleName);
}
