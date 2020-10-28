package com.root.Entity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.Entity.ERole;
import com.root.Entity.Role;




public interface RoleRepository  extends JpaRepository<Role, Long>{
	Optional<Role> findByname(ERole name);
}
