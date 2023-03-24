package com.ponomarev.CRUDApp.dao;

import com.ponomarev.CRUDApp.exceptions.NoSuchUserException;
import com.ponomarev.CRUDApp.models.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserUserDaoImpl implements UserDao<User, Long> {
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
    public User update(User user) throws NoSuchUserException {
       findById(user.getId()).orElseThrow(NoSuchUserException::new);
       return entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) throws NoSuchUserException {
        entityManager.remove(findById(id).orElseThrow(NoSuchUserException::new));
    }
}
