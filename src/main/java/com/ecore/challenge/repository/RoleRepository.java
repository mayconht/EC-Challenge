package com.ecore.challenge.repository;

import com.ecore.challenge.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query(value = "SELECT new Role(r.id, r.name) FROM Role r")
    List<Role> filteredFindAll();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.user=:user", nativeQuery = true)
    void deleteMemberRole(@Param("user") String user);

    @Query(value = "SELECT u.role_id FROM User u WHERE u.user=:user", nativeQuery = true)
    String findRoleByUserId(@Param("user") String user);

}
