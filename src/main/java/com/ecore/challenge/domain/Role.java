package com.ecore.challenge.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY) //TODO Descrever Sobre não trazer dados desnecessarios.
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String roleName;
    private String roleDescription;

    @ElementCollection
    @CollectionTable(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = "user")})
    private Set<String> user = new HashSet<>();

    public Role() {
    }

    public Role(String id, String roleName, String roleDescription) {
        this.id = id;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public Role(String id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Set<String> getUser() {
        return user;
    }

    public void setUser(Set<String> user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
