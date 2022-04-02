package com.ecore.challenge.resource;

import com.ecore.challenge.domain.Role;
import com.ecore.challenge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/roles")
public class RoleResource {

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Role>> findAllRoles() {
        List<Role> roleList = roleService.filteredFindAll();
        return ResponseEntity.ok().body(roleList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> findRoleById(@PathVariable String id) {
        Role roleObj = roleService.findById(id);
        return ResponseEntity.ok().body(roleObj);
    }

    @RequestMapping(value = "/name/{roleName}", method = RequestMethod.GET)
    public ResponseEntity<Role> findRoleByName(@PathVariable String roleName) {
        Role roleObj = roleService.findByRoleName(roleName);
        return ResponseEntity.ok().body(roleObj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createRole(@RequestBody Role roleObj) {
        roleObj = roleService.create(roleObj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(roleObj.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) //TODO Review Put Method
    public ResponseEntity<Role> updateRole(@PathVariable String id, @RequestBody Role roleObj) {
        roleObj.setId(id);
        roleObj.getUser().addAll(roleService.findById(id).getUser());
        roleService.update(roleObj);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/rolemembers/{roleId}/{memberId}", method = RequestMethod.PATCH)
    public ResponseEntity<Role> insertMemberRole(@PathVariable String roleId, @PathVariable String memberId) {
        roleService.insertMemberRole(roleId, memberId);
        return ResponseEntity.noContent().build();
    }

}
