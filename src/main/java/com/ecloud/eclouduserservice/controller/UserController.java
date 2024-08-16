package com.ecloud.eclouduserservice.controller;

import com.ecloud.eclouduserservice.model.UserDto;
import com.ecloud.eclouduserservice.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userServices;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userRequest) {
        logger.info("Creating new User: " + userRequest);
        UserDto userDto = userServices.createUser(userRequest);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userRequest) {
        logger.info("Updatingting new User: " + userRequest);
        UserDto userDto = userServices.updateUser(userRequest);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable String id) {
        logger.info("Getting User based on ID: " + id);
        UserDto userDto = userServices.getUser(id);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable String id) {
        logger.info("Deleting User based on UserID: " + id);
        UserDto userDto = userServices.deleteUser(id);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }


}
