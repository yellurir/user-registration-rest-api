package com.gamesys.codingtest.userregistrationrestapi.controller;

import com.gamesys.codingtest.userregistrationrestapi.model.User;
import com.gamesys.codingtest.userregistrationrestapi.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The UserRegistrationController class offers REST endpoint to register a User
 *  @author Revanth Yelluri
 */

@RestController
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    /**
     * The GET http method to call "/users/exclusion" endpoint is provided to retrieve all users in exclusion user list
     *  This endpoint is provided for demonstration purpose
     */
    @GetMapping("/users/exclusion")
    public List<User> getExcludedUsers(){
        return userRegistrationService.getAllExclusionUsers();
    }

    /**
     * The GET http method to call "/users" endpoint is provided to retrieve all the registered users
     *  This endpoint is provided for demonstration purpose
     */
    @GetMapping("/users")
    public List<User> getRegisteredUsers(){
        return userRegistrationService.getAllRegisterUsers();
    }

    /**
     * The POST http method to call "/users" endpoint is provided to register the users if not part of an exclusion list
     */
    @PostMapping("/users")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return new ResponseEntity<>(userRegistrationService.registerUser(user), HttpStatus.CREATED);
    }
}

