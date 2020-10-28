package com.root.EntityCV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.EntityCV.Technical;



public interface TechRepository extends JpaRepository<Technical, Integer> {
	Technical findByTechnicalNo(Integer technicalNo);
	
}
