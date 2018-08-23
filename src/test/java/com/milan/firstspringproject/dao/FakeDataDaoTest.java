package com.milan.firstspringproject.dao;
import com.milan.firstspringproject.model.User;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;

public class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;
    @Before
    public void setUp() throws Exception {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    public void shouldSelectAllUser() throws Exception{
        List<User> users = fakeDataDao.selectAllUser();
        User user = users.get(0);
        assertEquals(user.getFirstName(),"milan");
        assertEquals(user.getLastName(),"desai");
        assertEquals(user.getGender(), User.Gender.MALE);
        assertEquals(user.getEmail(),"md@abc.com");
        assertEquals(user.getAge(),22);
        assertNotEquals(user.getUserUid(), null);
    }

    @Test
    public void shouldSelectUserByUserId() throws Exception {
        UUID sUid = UUID.randomUUID();
        User user = new User(sUid,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");
        fakeDataDao.insertUser(sUid, user);
        List<User> users = fakeDataDao.selectAllUser();
        assertEquals(users.size(),2);

        Optional<User> sd = fakeDataDao.selectUserByUserId(sUid);
        assertEquals(sd.isPresent(),true);
        assertSame(sd.get(),user);
    }

    @Test
    public void updateUser() throws Exception{
    }

    @Test
    public void deleteUser() throws Exception{
        List<User> users = fakeDataDao.selectAllUser();
        User user = users.get(0);
        fakeDataDao.deleteUser(user.getUserUid());
        Optional<User> user2 = fakeDataDao.selectUserByUserId(user.getUserUid());
        assertEquals(user2.isPresent(),false);
    }

    @Test
    public void insertUser() {
    }
}