package com.example.easyDocs.AccessGroup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/group")
public class AccessGroupController {

    AccessGroupService accessGroupService;

    public AccessGroupController(AccessGroupService accessGroupService) {
        this.accessGroupService = accessGroupService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessGroup> getGroupById(@PathVariable Long id){
        AccessGroup accessGroup = accessGroupService.getGroupById(id);
        return ResponseEntity.status(HttpStatus.OK).body(accessGroup);
    }

    @PostMapping("/")
    public ResponseEntity<AccessGroup> createGroup(@RequestBody AccessGroup newGroup){
        AccessGroup savedGroup = accessGroupService.createGroup(newGroup);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedGroup.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable Long id){
        accessGroupService.deleteGroup(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateGroup(@PathVariable Long id, @RequestBody AccessGroup accessGroup){
        accessGroupService.updateGroup(id, accessGroup);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/addUser/{id}")
    public ResponseEntity<HttpStatus> addUserToGroup(@PathVariable("id") Long group_id,
                                                     @RequestParam(value = "user_id") Long user_id){
        accessGroupService.addUserToGroup(group_id,user_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/addDocument/{id}")
    public ResponseEntity<HttpStatus> addDocumentToGroup(@PathVariable("id") Long group_id,
                                                     @RequestParam(value = "document_id") Long document_id){
        accessGroupService.addDocumentToGroup(group_id,document_id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
