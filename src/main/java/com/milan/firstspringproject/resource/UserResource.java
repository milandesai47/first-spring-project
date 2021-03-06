package com.milan.firstspringproject.resource;

import com.milan.firstspringproject.model.User;
import com.milan.firstspringproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "api/v1/users"
)
public class UserResource {

    private UserService userService;
    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> fetchUsers(@QueryParam("gender") String gender)
    {
     return  userService.getAllUser(Optional.ofNullable(gender));
    }
    @RequestMapping
            (
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    path = "{userUid}"
            )
    public ResponseEntity<?> fetchUser(@PathVariable("userUid") UUID userUid) {
        return  userService.getUser(userUid).<ResponseEntity<?>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("user " + userUid + " not found")));
    }
    @RequestMapping(
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> insertNewUser(@RequestBody User user){
        int res = userService.insertUser(user);
    return getIntegerResponseEntity(res);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> updateUser(@RequestBody User user){
        int res = userService.updateUser(user);
        return getIntegerResponseEntity(res);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{userUid}"
    )
    public ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userId){
        int res = userService.deleteUser(userId);
        return getIntegerResponseEntity(res);
    }
    private ResponseEntity<Integer> getIntegerResponseEntity(int res) {
        if(res == 1){
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    class ErrorMessage{
        String errorMessage;

        public ErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
