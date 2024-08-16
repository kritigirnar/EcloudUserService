package com.ecloud.eclouduserservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    @NotBlank(message = "Username must not be blank")
    private String userName;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password should be at least 8 character")
    private String password;

    @NotNull(message = "Active status must not be null")
    private Boolean active;
}
