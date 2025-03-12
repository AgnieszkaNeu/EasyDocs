package com.example.easyDocs.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> returnUsers(){
        List<UserDto> users = userService.returnUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> returnUsersById(@PathVariable Long id){
        UserDto userDto = userService.returnUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PostMapping("")
    public ResponseEntity<UserDto> addUser(@RequestBody User user){
        User savedUser = userService.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> patchUpdateUser(@PathVariable Long id, @RequestBody User user, Authentication authentication){
        userService.patchUpdateUser(id,user, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id, Authentication authentication){
        userService.deleteUser(id, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/changeToAdmin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> changeUserToAdmin(@PathVariable Long id) {
        userService.changeUserToAdmin(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
