package com.example.easyDocs.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto userToUserDto(User user){
       return new UserDto(
               user.getFirst_name(),
               user.getLast_name(),
               user.getEmail(),
               user.getJob_title());
    }
}
