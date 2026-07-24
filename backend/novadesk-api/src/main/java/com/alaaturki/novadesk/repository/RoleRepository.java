package com.alaaturki.novadesk.repository;

import com.alaaturki.novadesk.entity.Role;
import com.alaaturki.novadesk.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(RoleType name);

}