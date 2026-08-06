package com.alaaturki.novadesk.repository;

import com.alaaturki.novadesk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.alaaturki.novadesk.dto.dashboard.DashboardUserResponse;
import com.alaaturki.novadesk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository
        extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
    long countByRole_Name(String roleName);
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    @Query("""
SELECT new com.alaaturki.novadesk.dto.dashboard.DashboardUserResponse(
    u.username,
    u.email,
    r.name
)
FROM User u
JOIN u.role r
""")
    List<DashboardUserResponse> findDashboardUsers();

}