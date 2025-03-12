package com.example.easyDocs.AccessGroup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/group")
public class AccessGroupController {

    AccessGroupService accessGroupService;

    public AccessGroupController(AccessGroupService accessGroupService) {
        this.accessGroupService = accessGroupService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AccessGroup>> getGroups(Authentication authentication){
        System.out.println(authentication.getAuthorities());
        List<AccessGroup> groups = accessGroupService.getGroups();
        return ResponseEntity.status(HttpStatus.OK).body(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessGroup> getGroupById(@PathVariable Long id, Authentication authentication){
        AccessGroup accessGroup = accessGroupService.getGroupById(id, authentication);
        return ResponseEntity.status(HttpStatus.OK).body(accessGroup);
    }

    @PostMapping("")
    public ResponseEntity<AccessGroup> createGroup(@RequestBody AccessGroup newGroup, Authentication authentication){
        AccessGroup savedGroup = accessGroupService.createGroup(newGroup, authentication);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedGroup.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable Long id, Authentication authentication){
        accessGroupService.deleteGroup(id,authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateGroup(@PathVariable Long id, @RequestBody AccessGroup accessGroup, Authentication authentication){
        System.out.println(accessGroup.getFounder());
        accessGroupService.updateGroup(id, accessGroup, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/addUser/{id}")
    public ResponseEntity<HttpStatus> addUserToGroup(@PathVariable("id") Long group_id,
                                                     @RequestParam(value = "user_id") Long user_id,
                                                     Authentication authentication){
        accessGroupService.addUserToGroup(group_id,user_id, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/addDocument/{id}")
    public ResponseEntity<HttpStatus> addDocumentToGroup(@PathVariable("id") Long group_id,
                                                     @RequestParam(value = "document_id") Long document_id,
                                                         Authentication authentication){
        accessGroupService.addDocumentToGroup(group_id,document_id, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
