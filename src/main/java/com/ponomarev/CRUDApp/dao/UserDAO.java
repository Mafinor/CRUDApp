package com.ponomarev.CRUDApp.dao;

import com.ponomarev.CRUDApp.exceptions.NoSuchUserException;
import com.ponomarev.CRUDApp.models.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO implements DAO<User, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override()
    public User update(Long id, User object) throws NoSuchUserException {
        User user = findById(id).orElseThrow(NoSuchUserException::new);
        user.setUsername(object.getUsername());
        user.setFirstName(object.getFirstName());
        user.setLastName(object.getLastName());
        user.setEmail(object.getEmail());
        return user;
    }

    @Override
    public void deleteById(Long id) throws NoSuchUserException {
        entityManager.remove(findById(id).orElseThrow(NoSuchUserException::new));
    }
}
