package com.gianca1994.springbootjwt.repository;

import com.gianca1994.springbootjwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
