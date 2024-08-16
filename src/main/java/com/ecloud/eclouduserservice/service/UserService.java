package com.ecloud.eclouduserservice.service;

import com.ecloud.eclouduserservice.model.UserDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {

    UserDto createUser(@Valid UserDto userRequest);

    UserDto updateUser(@Valid UserDto userRequest);

    UserDto getUser(String userId);

    UserDto deleteUser(String userId);

}
