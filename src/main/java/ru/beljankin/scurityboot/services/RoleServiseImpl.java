package ru.beljankin.scurityboot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beljankin.scurityboot.dao.RoleDAO;
import ru.beljankin.scurityboot.entities.Role;


@Service
@Transactional
public class RoleServiseImpl implements RoleServise{

    private RoleDAO roleDAO;
    public RoleServiseImpl(RoleDAO roleDAO){
        this.roleDAO = roleDAO;
    }

    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Override
    public Role findRoleByString(String roleName) {
        return roleDAO.findRoleByString(roleName);
    }
}
