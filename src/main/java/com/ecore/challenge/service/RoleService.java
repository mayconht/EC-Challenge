package com.ecore.challenge.service;

import com.ecore.challenge.domain.Role;
import com.ecore.challenge.repository.RoleRepository;
import com.ecore.challenge.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class RoleService {

    @Value("${defaultRoleValue}")
    private String defaultRoleValue;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserExternalService userExternalService;

    public List<Role> filteredFindAll() {
        return roleRepository.filteredFindAll();
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(final String id) {
        final Optional<Role> obj = roleRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Role not found: " + id + " Type: " + Role.class.getName()));
    }

    public Role findByRoleName(final String roleName) {
        final Optional<Role> obj = roleRepository.findByRoleName(roleName);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Role not found: " + roleName + " Type: " + Role.class.getName()));
    }

    public Role create(final Role roleObj) {
        roleObj.setId(UUID.randomUUID().toString());
        return roleRepository.save(roleObj);
    }

    public void update(final Role roleObj) {
        findById(roleObj.getId());
        roleRepository.save(roleObj);
    }

    public void insertMemberRole(final String roleId, final String memberId) {
        final Role role = findById(roleId);
        role.getUser().add(memberId);
        try {
            roleRepository.save(role);
        } catch (final Exception e) {
//            TODO Add log to it!!!
            roleRepository.deleteMemberRole(memberId);
            roleRepository.save(role);
        }
    }

    public void delete(final String id) {
        findById(id);
        roleRepository.deleteById(id);
    }

    public boolean verifyUserService(final String id) {
        try {
            return userExternalService.searchUserById(id);
        } catch (final Exception e) {
            throw new ObjectNotFoundException("User ID not found or User Service not available");
        }
    }


    public String findRoleByUserId(final String id) {
        final String roleId = roleRepository.findRoleByUserId(id);
        final Role role = findByRoleName(defaultRoleValue);
        if (roleId == null) {
            insertMemberRole(role.getId(), id);
            return role.getId();
        }
        return role.getId();

    }

}
