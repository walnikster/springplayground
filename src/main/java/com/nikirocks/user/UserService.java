package com.nikirocks.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void deleteUser(String username) {
        userDao.deleteByUsername(username);
    }
}
