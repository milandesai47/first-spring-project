package com.milan.firstspringproject.dao;

import com.milan.firstspringproject.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class FakeDataDao implements UserDao {

    private Map<UUID, User> database;


    public FakeDataDao() {
        database = new HashMap<>();
        UUID mUserId = UUID.randomUUID();
        UUID aUserId = UUID.randomUUID();
        database.put(mUserId, new User(mUserId,"milan", "desai", User.Gender.MALE, 25 , "md@abc.com"));
        database.put(aUserId, new User(aUserId,"ankita","dave",User.Gender.FEMALE, 25 , "ad@abc.com"));

    }

    @Override
    public List<User> selectAllUser() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<User> selectUserByUserId(UUID userId) {
        return Optional.ofNullable(database.get(userId));
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserUid(), user);
        return 1;
    }

    @Override
    public int deleteUser(UUID userId) {
        database.remove(userId);
        return 1;
    }

    @Override
    public int insertUser(UUID userId,User user) {
        database.put(userId, user);
        return 1;
    }
}
