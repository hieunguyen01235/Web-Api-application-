package com.root.EntityCV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.EntityCV.CvInfo;

public interface SampleRepository extends JpaRepository<CvInfo, Integer> {
	CvInfo findBycvNo(Integer id);

	CvInfo findBycvName(String name);

	Boolean existsByCvNo(Integer id);
	
	Boolean existsByCvName(String cvname);

}
