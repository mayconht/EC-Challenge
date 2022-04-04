package com.ecore.challenge.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = "user")})
    private Set<String> user = new HashSet<>();

    public Role() {
    }

    public Role(final String id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Role(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Set<String> getUser() {
        return user;
    }

    public void setUser(final Set<String> user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        final Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name, role.name) && Objects.equals(description, role.description) && Objects.equals(user, role.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, user);
    }
}
