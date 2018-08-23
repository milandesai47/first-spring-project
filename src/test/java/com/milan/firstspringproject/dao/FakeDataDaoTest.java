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
        assertTrue(sd.isPresent());
        assertSame(sd.get(),user);
       // assertThat(sd.isPresent()).;
    }

    @Test
    public void shouldNotSelectUserByRandomUserId() throws Exception {
       Optional<User> user = fakeDataDao.selectUserByUserId(UUID.randomUUID());
        assertFalse(user.isPresent());
    }

    @Test
    public void ShouldUpdateUser() throws Exception{
        UUID mUserUid = fakeDataDao.selectAllUser().get(0).getUserUid();
        User user = new User(mUserUid,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");
        fakeDataDao.updateUser(user);
        Optional<User> user1 = fakeDataDao.selectUserByUserId(mUserUid);
        assertTrue(user1.isPresent());
        assertNotEquals(user1,user);

    }

    @Test
    public void shouldDeleteUser() throws Exception{
        List<User> users = fakeDataDao.selectAllUser();
        User user = users.get(0);
        fakeDataDao.deleteUser(user.getUserUid());
        Optional<User> user2 = fakeDataDao.selectUserByUserId(user.getUserUid());
        assertFalse(user2.isPresent());
    }

    @Test
    public void shouldInsertUser() {
        UUID sUid = UUID.randomUUID();
        User user = new User(sUid,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");
        fakeDataDao.insertUser(sUid,user);
        List<User> users = fakeDataDao.selectAllUser();
        assertEquals(users.size(),2);
        assertSame(fakeDataDao.selectUserByUserId(sUid).get(),user);
    }
}