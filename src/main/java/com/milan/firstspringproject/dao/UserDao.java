package com.milan.firstspringproject.dao;
import com.milan.firstspringproject.model.User;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
public interface UserDao {
    List<User> selectAllUser();
    Optional<User> selectUserByUserId(UUID userId);
    int updateUser(User user);
    int deleteUser(UUID userId);
    int insertUser(UUID userId,User user);
}
