package com.milan.firstspringproject.dao;
import com.milan.firstspringproject.model.User;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

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
    public void selectUserByUserId() throws Exception {
    }

    @Test
    public void updateUser() throws Exception{
    }

    @Test
    public void deleteUser() throws Exception{
    }

    @Test
    public void insertUser() {
    }
}