package com.milan.firstspringproject.service;

import com.milan.firstspringproject.dao.UserDao;
import com.milan.firstspringproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {

    private UserDao userDao;
    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }


    public Optional<User> getUser(UUID userId) {
        return userDao.selectUserByUserId(userId);
    }


    public int updateUser(User user) {
       Optional<User> optionalUser = getUser(user.getUserUid());
       if(optionalUser.isPresent()){
           userDao.updateUser(user);
           return 1;
       }
        return -1;
    }


    public int deleteUser(UUID userId) {
        Optional<User> optionalUser = getUser(userId);
        if(optionalUser.isPresent()){
            userDao.deleteUser(userId);
            return 1;
        }
        return -1;
    }


    public int insertUser(User user) {
        UUID userId = UUID.randomUUID();
        user.setUserUid(userId);
        return userDao.insertUser(userId, user);
    }
}
