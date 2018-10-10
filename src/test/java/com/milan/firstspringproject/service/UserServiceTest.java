package com.milan.firstspringproject.service;

import com.milan.firstspringproject.dao.FakeDataDao;
import com.milan.firstspringproject.model.User;
import com.sun.javafx.collections.ImmutableObservableList;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
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
        List<User> allUser = userService.getAllUser(Optional.empty());

        assertEquals(allUser.size(),1);

        User user = allUser.get(0);

        assertUserFields(user);
    }

    @Test
    public void getUserByGender() {
        UUID sUid = UUID.randomUUID();
        User users1 = new User(sUid,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");
        UUID aUid = UUID.randomUUID();
        User users2 = new User(sUid,
                "abc",
                "desai",
                User.Gender.FEMALE,
                26,
                "abcd@abc.com");

        ImmutableList<User> users = new ImmutableList.Builder<User>()
                .add(users1)
                .add(users2)
                .build();
        given(fakeDataDao.selectAllUser()).willReturn(users);

        List<User> filteredUser = userService.getAllUser(Optional.of("male"));

        assertEquals(filteredUser.size(),1);
    }

    private void assertUserFields(User user) {
        assertEquals(user.getFirstName(), "setu");
        assertEquals(user.getLastName(), "desai");
        assertEquals(user.getGender(), User.Gender.MALE);
        assertEquals(user.getEmail(), "sd@abc.com");
        assertEquals(user.getAge(), 26);
        assertNotEquals(user.getUserUid(), null);
        }

    @Test
    public void throwExceptionOnInvalidGender() throws Exception{
        //TODO
    }

    @Test
    public void getUser() {
        UUID sUid = UUID.randomUUID();
        User users1 = new User(sUid,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");

        given(fakeDataDao.selectUserByUserId(sUid)).willReturn(Optional.of(users1));
        Optional<User> optionalUser = userService.getUser(sUid);

        assertTrue(optionalUser.isPresent());

       User user = optionalUser.get();

        assertUserFields(user);

    }

    @Test
    public void updateUser() {
        UUID sUid = UUID.randomUUID();
        User users1 = new User(sUid,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");

        given(fakeDataDao.selectUserByUserId(sUid)).willReturn(Optional.of(users1));
        given(fakeDataDao.updateUser(users1)).willReturn(1);

        int updateResult = userService.updateUser(users1);

        verify(fakeDataDao).selectUserByUserId(sUid);

        assertEquals(updateResult,1);
    }

    @Test
    public void deleteUser() {
        UUID sUid = UUID.randomUUID();
        User users1 = new User(sUid,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");
        given(fakeDataDao.selectUserByUserId(sUid)).willReturn(Optional.of(users1));
        given(fakeDataDao.deleteUser(sUid)).willReturn(1);

        int deleteResult = userService.deleteUser(sUid);

        verify(fakeDataDao).deleteUser(sUid);

        assertEquals(deleteResult,1);
    }

    @Test
    public void insertUser() {
        UUID sUid = UUID.randomUUID();
        User users1 = new User(null,
                "setu",
                "desai",
                User.Gender.MALE,
                26,
                "sd@abc.com");

       given(fakeDataDao.insertUser(any(UUID.class),eq(users1))).willReturn(1);

        ArgumentCaptor<User> capture = ArgumentCaptor.forClass(User.class);
       int insertResult = userService.insertUser(users1);
        verify(fakeDataDao).insertUser(any(UUID.class),capture.capture());
        User user = capture.getValue();
        assertUserFields(user);
     //  assertEquals(insertResult,1);
       //assertThat();
    }
}