package ru.beljankin.scurityboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.beljankin.scurityboot.entities.Role;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findRoleByString(String roleName) {
        return entityManager.createQuery("select role from Role role where role.name =:roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

}
