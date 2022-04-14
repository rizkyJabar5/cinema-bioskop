package com.rizky.challenge4.backend.data.mapper;

import com.rizky.challenge4.backend.data.dto.UserDto;
import com.rizky.challenge4.backend.data.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {
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
