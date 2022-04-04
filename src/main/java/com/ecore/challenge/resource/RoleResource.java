package com.ecore.challenge.resource;

import com.ecore.challenge.domain.Role;
import com.ecore.challenge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
        final List<Role> roleList = roleService.filteredFindAll();
        return ResponseEntity.ok().body(roleList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> findRoleById(@PathVariable final String id) {
        final Role roleObj = roleService.findById(id);
        return ResponseEntity.ok().body(roleObj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createRole(@RequestBody Role roleObj) {
        roleObj = roleService.create(roleObj);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(roleObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT) //TODO Review Put Method
    public ResponseEntity<Role> updateRole(@PathVariable final String id, @RequestBody final Role roleObj) {
        roleObj.setId(id);
        roleObj.getUser().addAll(roleService.findById(id).getUser());
        roleService.update(roleObj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/changeuserrole/{roleId}/user/{userId}", method = RequestMethod.PATCH)
    public ResponseEntity<Role> insertUserRole(@PathVariable final String roleId, @PathVariable final String userId) {
        roleService.verifyUserService(userId);
        roleService.insertUserRole(roleId, userId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/team/{teamId}/role/{roleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeTeamRole(@PathVariable final String teamId, @PathVariable final String roleId) {
        roleService.changeTeamRole(teamId, roleId);
        return ResponseEntity.noContent().build();
    }

}
