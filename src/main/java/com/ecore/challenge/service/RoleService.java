package com.ecore.challenge.service;

import com.ecore.challenge.domain.Role;
import com.ecore.challenge.repository.RoleRepository;
import com.ecore.challenge.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> filteredFindAll() {
        return roleRepository.filteredFindAll();
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(String id) {
        Optional<Role> obj = roleRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Role not found: " + id + " Type: " + Role.class.getName()));
    }

    public Role findByRoleName(String roleName) {
        Optional<Role> obj = roleRepository.findByRoleName(roleName);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Role not found: " + roleName + " Type: " + Role.class.getName()));
    }

    public Role create(Role roleObj) {
        roleObj.setId(UUID.randomUUID().toString());
        return roleRepository.save(roleObj);
    }

    public void update(Role roleObj) {
        findById(roleObj.getId());
        roleRepository.save(roleObj);
    }

    public void insertMemberRole(String roleId, String memberId) {
        Role role = findById(roleId);
        role.getUser().add(memberId);
        try {
            roleRepository.save(role);
        } catch (Exception e) {
//            TODO Add log to it!!!
            roleRepository.deleteOldMemberRole(memberId);
            roleRepository.save(role);
        }
    }

    public void delete(String id) {
        findById(id);
        roleRepository.deleteById(id);
    }
}
