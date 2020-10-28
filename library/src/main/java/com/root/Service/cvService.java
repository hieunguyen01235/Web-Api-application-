package com.root.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.root.EntityCV.Certificate;
import com.root.EntityCV.CvInfo;
import com.root.EntityCV.Education;
import com.root.EntityCV.FieldOfStudy;
import com.root.EntityCV.Technical;
import com.root.payout.Request.SubmitCvRequest;

public interface cvService {
	List<CvInfo> findAllCV();
	List<Technical> findAllTech();

	Optional<CvInfo> findById(Integer id);

	void save(CvInfo cvinfo);

	ResponseEntity<?>  edit(Integer id, SubmitCvRequest submitCvRequest);

	void remove(Integer id);

	CvInfo showcvById(Integer id);

//	Optional<Certificate> findCerById(Integer id);
//
//	Optional<FieldOfStudy> findFielById(Integer id);
//
//	Optional<Technical> findTechById(Integer id);
//
//	Optional<Education> findEduById(Integer id);

	
	void addNewCv(SubmitCvRequest submitCvRequest);
	
	CvInfo submitCv(SubmitCvRequest submitCvRequest);
	
//	SubmitCvRequest showall ();
}
