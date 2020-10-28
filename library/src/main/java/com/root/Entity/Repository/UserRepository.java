package com.root.Entity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.Entity.User;




public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String userName);
}
