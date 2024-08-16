package com.ecloud.eclouduserservice.mapper;

import com.ecloud.eclouduserservice.model.User;
import com.ecloud.eclouduserservice.model.UserDto;

public class UserServicesMapper {

    public static User toEntity(UserDto userRequest) {

        User user = new User();
        user.setId(userRequest.getId());
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setActive(userRequest.getActive() != null ? userRequest.getActive() : Boolean.TRUE);

        return user;
    }

    public static UserDto toDTO(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setActive(user.getActive());

        return userDto;
    }
}
