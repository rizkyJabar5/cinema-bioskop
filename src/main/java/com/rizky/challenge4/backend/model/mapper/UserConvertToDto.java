package com.rizky.challenge4.backend.model.mapper;

import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.model.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserConvertToDto {

    public UserDto convertEntityToDto(Users user) {
        UserDto userdto = new UserDto();
        userdto.setUserId(user.getId());
        userdto.setUsername(user.getUsername());
        userdto.setEmail(user.getEmail());
        userdto.setPassword(user.getPassword());
        userdto.setAddress(user.getAddress());
        return userdto;
    }
}
