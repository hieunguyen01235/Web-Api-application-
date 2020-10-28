package com.root.EntityCV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.EntityCV.FieldOfStudy;


public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, Integer>{
	FieldOfStudy findByStudyNo(Integer studyNo);
	
}
