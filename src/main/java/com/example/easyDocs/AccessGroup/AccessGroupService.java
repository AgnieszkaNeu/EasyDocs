package com.example.easyDocs.AccessGroup;

import com.example.easyDocs.exceptions.AccessException;
import org.springframework.stereotype.Service;

@Service
public class AccessGroupService {

    AccessGroupRepository accessGroupRepository;

    public AccessGroupService(AccessGroupRepository accessGroupRepository) {
        this.accessGroupRepository = accessGroupRepository;
    }

    public AccessGroup getGroupById(Long id) {
        return accessGroupRepository.findById(id).orElseThrow(AccessException::new);
    }

    public AccessGroup createGroup(AccessGroup newGroup) {
        return accessGroupRepository.save(newGroup);
    }

    public void deleteGroup(Long id) {
        AccessGroup accessGroup = accessGroupRepository.findById(id).orElseThrow(AccessException::new);
        accessGroupRepository.delete(accessGroup);
    }

    public void updateGroup(Long id, AccessGroup accessGroup) {
        AccessGroup accessGroupToUpdate = accessGroupRepository.findById(id).orElseThrow(AccessException::new);

        if(accessGroup.getName()!=null){
            accessGroupToUpdate.setName(accessGroup.getName());
        }

        if(accessGroup.getInitiator()!=null){
            accessGroupToUpdate.setInitiator(accessGroup.getInitiator());
        }

        accessGroupRepository.save(accessGroupToUpdate);
    }

    public void addUserToGroup(Long group_id, Long user_id) {
        accessGroupRepository.addUser(user_id,group_id);
    }

    public void addDocumentToGroup(Long group_id, Long document_id) {
        accessGroupRepository.addDocument(document_id, group_id);
    }
}
