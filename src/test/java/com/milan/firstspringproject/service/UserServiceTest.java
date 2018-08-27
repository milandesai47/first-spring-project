package com.milan.firstspringproject.service;

import com.milan.firstspringproject.dao.FakeDataDao;
import com.milan.firstspringproject.model.User;
import com.sun.javafx.collections.ImmutableObservableList;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.UUID;


public class UserServiceTest {
    @Mock
    private FakeDataDao fakeDataDao;
    private UserService userService;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService= new UserService(fakeDataDao);
    }

    @Test
    public void getAllUser() {
        UUID sUid = UUID.randomUUID();
        User users1 = new User(sUid,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");

        ImmutableList<User> users = new ImmutableList.Builder<User>()
                .add(users1)
                .build();
        given(fakeDataDao.selectAllUser()).willReturn(users);
        List<User> allUser = userService.getAllUser();

        assertEquals(allUser.size(),1);

        User user = allUser.get(0);

        assertEquals(user.getFirstName(),"setu");
        assertEquals(user.getLastName(),"desai");
        assertEquals(user.getGender(), User.Gender.MALE);
        assertEquals(user.getEmail(),"sd@abc.com");
        assertEquals(user.getAge(),26);
        assertNotEquals(user.getUserUid(), null);
    }

    @Test
    public void getUser() {
      }

    @Test
    public void updateUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void insertUser() {
    }
}