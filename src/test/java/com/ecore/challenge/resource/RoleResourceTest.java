package com.ecore.challenge.resource;

import com.ecore.challenge.builder.RoleMockBuilder;
import com.ecore.challenge.domain.Role;
import com.ecore.challenge.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class RoleResourceTest {

    private static final String UUID = "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c";
    List<Role> roles;
    ResponseEntity<List<Role>> roleList;
    Role role;
    @InjectMocks
    private RoleResource roleResource;
    @Mock
    private RoleService roleService;

    @BeforeEach
    private void mockRole() {
        role = new Role();
        roles = new ArrayList<>();

    }

    @Test
    public void findAllRoles_ShouldReturnAllRoles() {

        roles.add(RoleMockBuilder.getBuilder().mockRole().build());
        Mockito.when(roleService.filteredFindAll()).thenReturn(roles);
        roleList = roleResource.findAllRoles();
        final Role role = Objects.requireNonNull(roleList.getBody()).get(0);

        Assertions.assertTrue(roles.size() > 0);
        Assertions.assertEquals(roles.get(0), role);
        Assertions.assertEquals(roleList.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void findRoleById_ShouldReturnAUniqueRole() {

        roles.add(RoleMockBuilder.getBuilder().mockRole().withId(UUID).withRoleName("Developer").withRoleDesc("Developer Role").build());
        Mockito.when(roleService.findById(UUID)).thenReturn(role);

        final ResponseEntity<Role> roleEntity = roleResource.findRoleById(UUID);
        final Role role = Objects.requireNonNull(roleEntity.getBody());

        Assertions.assertEquals(roleEntity.getBody(), role);
        Assertions.assertEquals(roleEntity.getStatusCode(), HttpStatus.OK);
    }


}