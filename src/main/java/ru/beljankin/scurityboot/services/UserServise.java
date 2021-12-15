package ru.beljankin.scurityboot.services;




import ru.beljankin.scurityboot.entities.User;

import java.util.List;

public interface UserServise {
List<User> getAll();
    User select(long id);
void save(User person);
void delete(long id);
void update(long id, User person);
    User findByUserName(String username);
}
