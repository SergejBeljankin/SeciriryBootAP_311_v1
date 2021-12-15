package ru.beljankin.scurityboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.beljankin.scurityboot.dao.UserDAO;
import ru.beljankin.scurityboot.entities.User;

import java.util.List;

@Service
@Transactional
public class UserServiseImpl implements UserServise{

    private UserDAO userDAO;

    private BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

    public UserServiseImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public User select(long id) {
        return userDAO.select(id);
    }

    @Override
    public void save(User person) {
        person.setPassword(bCryptPasswordEncoder().encode(person.getPassword()));
        userDAO.save(person);
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }


    @Override
    public void update(long id, User person) {
        person.setPassword(bCryptPasswordEncoder().encode(person.getPassword()));
        userDAO.update(id, person);
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User findByUserName(String username) {
        return userDAO.findByUserName(username);
    }
}
