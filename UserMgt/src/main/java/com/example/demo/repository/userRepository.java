package com.example.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer>{

	Optional<user> findByPassword(String password);
	

	user findByUsername(String name);

	
}
