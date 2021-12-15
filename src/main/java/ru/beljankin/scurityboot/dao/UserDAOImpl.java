package ru.beljankin.scurityboot.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.beljankin.scurityboot.entities.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll(){
        return entityManager.createQuery("from Person", User.class).getResultList();
    }


    @Override
    public User select(long id){
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User person){
        entityManager.persist(person);
    }

    @Override
    public void delete(long id){
        entityManager.remove(select(id));
    }

    @Override
    public void update(long id, User personVariable){
        User person = select(id);
        person.setUsername(personVariable.getUsername());
        person.setPassword(personVariable.getPassword());//
//        person.setPassword();//
        person.setSurname(personVariable.getSurname());
        person.setName(personVariable.getName());
        person.setRoles(personVariable.getRoles());
        save(person);
    }



    @Override
    public List<User> findPersonByRole(String roleName) {
        return entityManager.createQuery("select person from Person person inner join Role role on person.id = role.id where role.rolesName = :roleName", User.class)
                .setParameter("roleName", roleName).getResultList();
    }

//    @Override
//    public void setRoles(Set<Role> roleSet) {
//    }
    public User findByUserName(String username){
        return entityManager.createQuery("select person from Person as person where person.username =:username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}
