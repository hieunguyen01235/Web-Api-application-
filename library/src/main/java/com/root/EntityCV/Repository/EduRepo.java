package com.root.EntityCV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.EntityCV.Education;

public interface EduRepo extends JpaRepository<Education, Integer> {
	Education findBySchoolNo(Integer schoolNo);

}
