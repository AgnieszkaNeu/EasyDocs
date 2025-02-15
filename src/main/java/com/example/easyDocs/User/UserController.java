package com.example.easyDocs.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> returnUsers(){
        List<UserDto> users = userService.returnUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> returnUsersById(@PathVariable Long id){
        UserDto userDto = userService.returnUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> addUser(@RequestBody User user){
        User savedUser = userService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<HttpStatus> patchUpdateUser(@PathVariable Long id, @RequestBody User user){
        userService.patchUpdateUser(id,user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<HttpStatus> putUpdateUser(@PathVariable Long id, @RequestBody User user){
        userService.putUpdateUser(id,user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/user/changeToAdmin/{id}")
    public ResponseEntity<HttpStatus> changeUserToAdmin(@PathVariable Long id) {
        userService.changeUserToAdmin(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
