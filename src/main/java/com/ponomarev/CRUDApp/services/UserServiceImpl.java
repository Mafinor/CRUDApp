package com.ponomarev.CRUDApp.services;

import com.ponomarev.CRUDApp.dao.UserDaoImpl;
import com.ponomarev.CRUDApp.exceptions.NoSuchUserException;
import com.ponomarev.CRUDApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDaoImpl;

    @Autowired
    public UserServiceImpl(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Override
    public List<User> findAll() {
        return userDaoImpl.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDaoImpl.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userDaoImpl.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User updatedUser) {
        User user;
        try {
            user = userDaoImpl.update(id, updatedUser);
        } catch (NoSuchUserException e) {
            return null;
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        try {
            userDaoImpl.deleteById(id);
        } catch (NoSuchUserException ignore) {
        }
    }
}
