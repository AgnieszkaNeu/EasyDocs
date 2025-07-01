package com.example.easyDocs.AccessGroup;

import com.example.easyDocs.User.User;
import com.example.easyDocs.exceptions.AccessException;
import com.example.easyDocs.exceptions.AccessGroupException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AccessGroupService {

    AccessGroupRepository accessGroupRepository;

    public AccessGroupService(AccessGroupRepository accessGroupRepository) {
        this.accessGroupRepository = accessGroupRepository;
    }

    public User getAuthenticatedUser(Authentication authentication){
        return (User) authentication.getPrincipal();
    }

    public boolean isAdminOrFounder(User user, Long id){
        return user.getRole().equals("ROLE_ADMIN") || id.equals(user.getId());
    }

    public List<AccessGroup> getGroups(){
        return accessGroupRepository.findAll();
    }

    public AccessGroup getGroupById(Long id, Authentication authentication) {
        AccessGroup accessGroup = accessGroupRepository.findById(id).orElseThrow(() -> new AccessGroupException(id));
        User user = getAuthenticatedUser(authentication);

        Set<User> users  = accessGroup.getUsers();
        List<Long> ids = users.stream().map(User::getId).toList();

        if (ids.contains(user.getId()) || user.getRole().equals("ROLE_ADMIN")){
            return accessGroup;
        } else {
            throw new AccessException();
        }
    }

    public AccessGroup createGroup(AccessGroup newGroup, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        newGroup.setFounder(user);

        AccessGroup saved = accessGroupRepository.save(newGroup);
        accessGroupRepository.addUser(user.getId(), saved.getId());

        return saved;
    }

    public void deleteGroup(Long id, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        AccessGroup accessGroup = accessGroupRepository.findById(id).orElseThrow(AccessException::new);
        if(isAdminOrFounder(user,accessGroup.getFounder().getId())){
            accessGroupRepository.delete(accessGroup);
        } else {
            throw new AccessException();
        }
    }

    public void updateGroup(Long id, AccessGroup accessGroup, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        AccessGroup accessGroupToUpdate = accessGroupRepository.findById(id).orElseThrow(AccessException::new);
        if(!(isAdminOrFounder(user,accessGroupToUpdate.getFounder().getId()))){
            throw new AccessException();
        }
        if(accessGroup.getName()!=null){
            accessGroupToUpdate.setName(accessGroup.getName());
        }
        accessGroupRepository.save(accessGroupToUpdate);
    }

    public void addUserToGroup(Long group_id, Long user_id, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        AccessGroup accessGroup = accessGroupRepository.findById(group_id).orElseThrow(() -> new AccessGroupException(group_id));
        if(isAdminOrFounder(user,accessGroup.getFounder().getId())) {
            accessGroupRepository.addUser(user_id, group_id);
        } else {
            throw new AccessException();
        }
    }

    public void addDocumentToGroup(Long group_id, Long document_id, Authentication authentication) {
        User user = getAuthenticatedUser(authentication);
        AccessGroup accessGroup = accessGroupRepository.findById(group_id).orElseThrow(() -> new AccessGroupException(group_id));

        Set<User> users  = accessGroup.getUsers();
        List<Long> ids = users.stream().map(User::getId).toList();

        if (ids.contains(user.getId()) || user.getRole().equals("ROLE_ADMIN")){
            accessGroupRepository.addDocument(document_id, group_id);
        }else{
            throw new AccessException();
        }
    }
}
