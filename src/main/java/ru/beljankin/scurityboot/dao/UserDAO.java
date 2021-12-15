package ru.beljankin.scurityboot.dao;

import ru.beljankin.scurityboot.entities.User;


import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User select(long id);
    void save(User person);
    void delete(long id);
    void update(long id, User person);
//    public void setRoles(Set<Role> roleSet);
    List<User> findPersonByRole(String roleName);
    User findByUserName(String username);
}
