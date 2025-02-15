package com.example.easyDocs.User;

import com.example.easyDocs.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService {

    UserMapper userMapper;

    UserRepository userRepository;

    public UserService (UserMapper userMapper, UserRepository userRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<UserDto> returnUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.userToUserDto(user))
                .collect(Collectors.toList());
    }

    public UserDto returnUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.userToUserDto(user);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void patchUpdateUser(Long id, User updateUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if(updateUser.getEmail() != null){
            user.setEmail(updateUser.getEmail());
        }
        if(updateUser.getFirst_name() != null){
            user.setFirst_name(updateUser.getFirst_name());
        }
        if(updateUser.getLast_name() != null){
            user.setLast_name(updateUser.getLast_name());
        }
        if(updateUser.getPhone_number() != null){
            user.setPhone_number(updateUser.getPhone_number());
        }
        if(updateUser.getPassword() != null){
            user.setPassword(updateUser.getPassword());
        }
        if(updateUser.getJob_title() != null){
            user.setJob_title(updateUser.getJob_title());
        }
        if(updateUser.getDescription() != null){
            user.setDescription(updateUser.getDescription());
        }

        userRepository.save(user);
    }

    public void putUpdateUser(Long id, User updateUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setEmail(updateUser.getEmail());
        user.setFirst_name(updateUser.getFirst_name());
        user.setLast_name(updateUser.getLast_name());
        user.setPassword(updateUser.getPassword());
        user.setUser_creation_date(updateUser.getUser_creation_date());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    public void changeUserToAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setRole("ADMIN");
        userRepository.save(user);
    }
}
