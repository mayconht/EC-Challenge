package com.ecore.challenge.builder;

import com.ecore.challenge.domain.Role;

public class RoleMockBuilder {

    private static final Role role = new Role();

    public RoleMockBuilder() {
    }

    public static RoleMockBuilder getBuilder() {
        return new RoleMockBuilder();
    }

    public RoleMockBuilder withRoleName(final String roleName) {
        role.setName(roleName);
        return this;
    }

    public RoleMockBuilder withRoleDesc(final String roleDesc) {
        role.setDescription(roleDesc);
        return this;
    }

    public RoleMockBuilder withId(final String Id) {
        role.setId(Id);
        return this;
    }

    public RoleMockBuilder mockRole() {
        role.setName("Developer");
        role.setDescription("Developer Desc");
        return this;
    }

    public Role build() {
        return role;
    }

}
