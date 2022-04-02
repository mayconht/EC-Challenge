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

    @RequestMapping(value = "/name/{roleName}", method = RequestMethod.GET)
    public ResponseEntity<Role> findRoleByName(@PathVariable final String roleName) {
        final Role roleObj = roleService.findByRoleName(roleName);
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/changeuserrole/{roleId}/user/{memberId}", method = RequestMethod.PATCH)
    public ResponseEntity<Role> insertMemberRole(@PathVariable final String roleId, @PathVariable final String memberId) {
        roleService.insertMemberRole(roleId, memberId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserWithRole(@PathVariable final String userId){
        roleService.verifyUserService(userId);
        return ResponseEntity.ok().body("{ id : " + roleService.findRoleByUserId(userId)+ "}"); // TODO: Convert to JSON
    }

}
