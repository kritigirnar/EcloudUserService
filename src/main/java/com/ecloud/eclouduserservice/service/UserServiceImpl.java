package com.ecloud.eclouduserservice.service;

import com.ecloud.eclouduserservice.exception.CustomGlobalException;
import com.ecloud.eclouduserservice.exception.CustomGlobalMessages;
import com.ecloud.eclouduserservice.mapper.UserServicesMapper;
import com.ecloud.eclouduserservice.model.User;
import com.ecloud.eclouduserservice.model.UserDto;
import com.ecloud.eclouduserservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userRequest) {
        log.info("Saving the User details: {}", userRequest);
        try {
            User user = UserServicesMapper.toEntity(userRequest);
            User savedUser = userRepository.save(user);
            return UserServicesMapper.toDTO(savedUser);
        } catch (Exception e) {
            log.error("Error occurred while saving user: ", e);
            throw new CustomGlobalException(
                    CustomGlobalMessages.ERROR_OCCURED_WHILE_SAVING_CODE,
                    CustomGlobalMessages.ERROR_OCCURED_WHILE_SAVING_MESSAGE
            );
        }
    }

    @Override
    public UserDto updateUser(UserDto userRequest) {
        log.info("Updating the User details: {}", userRequest);
        if(ObjectUtils.isEmpty(userRequest.getId()))
        {
            throw new CustomGlobalException(CustomGlobalMessages.ERROR_USER_ID_NOT_FOUND_CODE,
                    String.format(CustomGlobalMessages.ERROR_USER_ID_NOT_FOUND_MESSAGE, userRequest.getId()));
        }
        try {
            User existingUser = userRepository.findById(userRequest.getId())
                    .orElseThrow(() -> new CustomGlobalException(
                            CustomGlobalMessages.ERROR_USER_NOT_FOUND_CODE,
                            String.format(CustomGlobalMessages.ERROR_USER_NOT_FOUND_MESSAGE, userRequest.getId())));

            existingUser.setUserName(userRequest.getUserName());
            existingUser.setPassword(userRequest.getPassword());
            existingUser.setActive(userRequest.getActive());

            User updatedUser = userRepository.save(existingUser);
            return UserServicesMapper.toDTO(updatedUser);
        } catch (Exception e) {
            log.error("Error occurred while updating user: ", e);
            throw new CustomGlobalException(
                    CustomGlobalMessages.ERROR_OCCURED_WHILE_UPDATING_CODE,
                    CustomGlobalMessages.ERROR_OCCURED_WHILE_UPDATING_MESSAGE
            );
        }
    }

    @Override
    public UserDto getUser(String userId) {
        log.info("Getting the user based on user ID: {}", userId);
        return userRepository.findById(Long.valueOf(userId))
                .map(UserServicesMapper::toDTO)
                .orElseThrow(() -> new CustomGlobalException(
                        CustomGlobalMessages.ERROR_USER_NOT_FOUND_CODE,
                        String.format(CustomGlobalMessages.ERROR_USER_NOT_FOUND_MESSAGE, userId)
                ));
    }

    @Override
    public UserDto deleteUser(String userId) {
        log.info("Deleting the user based on user ID: {}", userId);
        User user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new CustomGlobalException(
                        CustomGlobalMessages.ERROR_USER_NOT_FOUND_CODE,
                        String.format(CustomGlobalMessages.ERROR_USER_NOT_FOUND_MESSAGE, userId)
                ));
        userRepository.deleteById(user.getId());
        log.info("User deleted successfully: {}", userId);
        return UserServicesMapper.toDTO(user);
    }
}
