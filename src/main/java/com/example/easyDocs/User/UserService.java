package com.example.easyDocs.User;

import com.example.easyDocs.AccessGroup.AccessGroup;
import com.example.easyDocs.AccessGroup.AccessGroupRepository;
import com.example.easyDocs.Document.Document;
import com.example.easyDocs.Document.DocumentRepository;
import com.example.easyDocs.exceptions.AccessException;
import com.example.easyDocs.exceptions.AccessGroupException;
import com.example.easyDocs.exceptions.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserService {

    UserMapper userMapper;
    UserRepository userRepository;
    DocumentRepository documentRepository;
    AccessGroupRepository accessGroupRepository;

    public UserService (UserMapper userMapper, UserRepository userRepository, DocumentRepository documentRepository, AccessGroupRepository accessGroupRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.accessGroupRepository = accessGroupRepository;
    }

    public User getAuthenticatedUser(Authentication authentication){
        return (User) authentication.getPrincipal();
    }

    public boolean isAdminOrOwner(User authenticated, User entity){
        return Objects.equals(authenticated.getRole(), "ADMIN") || Objects.equals(entity.getId(), authenticated.getId());
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

    public void patchUpdateUser(Long id, User updateUser, Authentication authentication) {

        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        User authenticatedUser = getAuthenticatedUser(authentication);

        if (!isAdminOrOwner(authenticatedUser, userToUpdate)){
            throw new AccessException();
        }

        if(updateUser.getEmail() != null){
            userToUpdate.setEmail(updateUser.getEmail());
        }
        if(updateUser.getFirst_name() != null){
            userToUpdate.setFirst_name(updateUser.getFirst_name());
        }
        if(updateUser.getLast_name() != null){
            userToUpdate.setLast_name(updateUser.getLast_name());
        }
        if(updateUser.getPhone_number() != null){
            userToUpdate.setPhone_number(updateUser.getPhone_number());
        }
        if(updateUser.getPassword() != null){
            userToUpdate.setPassword(updateUser.getPassword());
        }
        if(updateUser.getJob_title() != null){
            userToUpdate.setJob_title(updateUser.getJob_title());
        }
        if(updateUser.getDescription() != null){
            userToUpdate.setDescription(updateUser.getDescription());
        }

        userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id, Authentication authentication) {
        User userToDelete = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        User authenricatedUser = getAuthenticatedUser(authentication);

        if(!isAdminOrOwner(authenricatedUser, userToDelete)){
            throw new AccessException();
        }

        Set<Document> documents = documentRepository.findAllByCreator(userToDelete);
        for (Document document: documents){
            document.setCreator(null);
            documentRepository.save(document);
        }

        Set<Long> groups_id = accessGroupRepository.findGroupsUserBelongTo(id);
        Set<AccessGroup>accessGroups = new HashSet<>();

        for(Long group_id: groups_id){
            AccessGroup accessGroup = accessGroupRepository.findById(group_id).orElseThrow(() -> new AccessGroupException(group_id));
            accessGroups.add(accessGroup);
        }

        for (AccessGroup accessGroup: accessGroups){
            Set<User> users = accessGroup.getUsers();
            users.remove(userToDelete);
            accessGroup.setUsers(users);
            accessGroupRepository.save(accessGroup);
        }

        userRepository.delete(userToDelete);
    }

    public void changeUserToAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        user.setRole("ADMIN");
        userRepository.save(user);
    }
}
