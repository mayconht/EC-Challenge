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

    @Autowired
    private TeamExternalService teamExternalService;

    public List<Role> filteredFindAll() {
        return roleRepository.filteredFindAll();
    }

    public Role findById(final String id) {
        final Optional<Role> obj = roleRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Role not found: " + id + " Type: " + Role.class.getName()));
    }

    public Role create(final Role roleObj) {
        roleObj.setId(UUID.randomUUID().toString());
        return roleRepository.save(roleObj);
    }

    public void update(final Role roleObj) {
        findById(roleObj.getId());
        roleRepository.save(roleObj);
    }

    public void insertUserRole(final String roleId, final String memberId) {
        final Role role = findById(roleId);
        role.getUser().add(memberId);
        try {
            roleRepository.save(role);
        } catch (final Exception e) {
            roleRepository.deleteMemberRole(memberId);
            roleRepository.save(role);
        }
    }

    public void verifyUserService(final String id) {
        try {
            userExternalService.hasUserById(id);
        } catch (final Exception e) {
            throw new ObjectNotFoundException("User ID not found or User Service not available");
        }
    }

    public void changeTeamRole(final String teamId, final String roleId) {
        try {
            final List<String> ls = teamExternalService.searchTeamById(teamId);
            ls.forEach(item -> insertUserRole(roleId, item));
        } catch (final Exception e) {
            throw new ObjectNotFoundException("Team ID not found or Team Service not available");
        }
    }
}