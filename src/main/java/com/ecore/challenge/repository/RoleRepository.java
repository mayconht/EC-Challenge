package com.ecore.challenge.repository;

import com.ecore.challenge.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByRoleName(String roleName);

    @Query(value = "Select new Role(r.id, r.roleName) from Role r")
    List<Role> filteredFindAll();

    @Transactional
    @Modifying
    @Query(value = "delete from User u where u.user=:user", nativeQuery = true)
    void deleteOldMemberRole(@Param("user") String user);
}
