package com.ponomarev.CRUDApp.services;

import com.ponomarev.CRUDApp.dao.UserDAO;
import com.ponomarev.CRUDApp.exceptions.NoSuchUserException;
import com.ponomarev.CRUDApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User updatedUser) {
        User user;
        try {
            user = userDAO.update(id, updatedUser);
        } catch (NoSuchUserException e) {
            return null;
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteByUd(Long id) {
        try {
            userDAO.deleteById(id);
        } catch (NoSuchUserException ignore) {
        }
    }
}
