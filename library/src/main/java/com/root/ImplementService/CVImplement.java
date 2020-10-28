package com.root.ImplementService;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.root.EntityCV.Certificate;
import com.root.EntityCV.CvInfo;
import com.root.EntityCV.Education;
import com.root.EntityCV.FieldOfStudy;
import com.root.EntityCV.Technical;
import com.root.EntityCV.Repository.*;
import com.root.Service.cvService;
import com.root.payout.Request.SubmitCvRequest;
import com.root.payout.messeger.ResponseMessage;



@Service
public class CVImplement implements cvService {

	@Autowired
	private SampleRepository cvRepository;
	@Autowired
	private TechRepository tectR;
	@Autowired
	private FieldOfStudyRepository FOSrepository;
	@Autowired
	private CertificateRepository cerReponsitory;
	@Autowired
	private EduRepo eduRepository;

	@Override
	public List<CvInfo> findAllCV() {
		return (List<CvInfo>) cvRepository.findAll();

	}

	@Override
	public Optional<CvInfo> findById(Integer id) {

		return cvRepository.findById(id);
	}

	@Override
	public void addNewCv(SubmitCvRequest submitCvRequest) {
	}

	@Override
	public void save(CvInfo cvinfo) {

		cvRepository.save(cvinfo);
	}

	public void addIntoDB(Integer id, SubmitCvRequest submitCvRequest) {
		CvInfo cvInfo = new CvInfo();

		cvInfo.setCvNo(id);
		cvInfo.setCvName(submitCvRequest.getCvname());
		cvInfo.setSex(1);
		if (submitCvRequest.getGender().equals("Nam")) {
			cvInfo.setSex(0);
		}
		cvInfo.setAboutMe(submitCvRequest.getAboutMe());
		cvInfo.setPhoneNum(submitCvRequest.getPhoneNum());
		cvInfo.setImgPath(submitCvRequest.getImg_path());
		cvInfo.setAddress(submitCvRequest.getAddress());
		cvInfo.setDegree(submitCvRequest.getDegree());
		cvInfo.setTechnicalLevel(submitCvRequest.getTechnicalLevel());
		cvInfo.setCompleteDegree(submitCvRequest.getCompleteDegree());
		cvInfo.setYearStart(submitCvRequest.getYearStart());
		cvInfo.setYearEnd(submitCvRequest.getYearEnd());
		cvInfo.setExperence_month(submitCvRequest.getExperence_month());
		cvInfo.setExperenceYear(submitCvRequest.getExperenceYear());
		cvInfo.setIssuseDate(submitCvRequest.getIssuseDate());

		cvRepository.save(cvInfo);

		Education edu = new Education();
		edu.setSchoolNo(id);
		edu.setSchoolName(submitCvRequest.getSchoolname());

		Technical tech = new Technical();
		tech.setTechnicalNo(cvInfo.getCvNo());
		tech.setSkillName(submitCvRequest.getSkillname());

		FieldOfStudy fos = new FieldOfStudy();
		fos.setStudyNo(cvInfo.getCvNo());
		fos.setStudyName(submitCvRequest.getStudyname());

		Certificate cer = new Certificate();
		cer.setCertificatNo(cvInfo.getCvNo());
		cer.setCertificatName(submitCvRequest.getCertificatName());
		cer.setAuthority(submitCvRequest.getAuthority());
		cer.setLicenseNumber(submitCvRequest.getLicenseNumber());

		eduRepository.save(edu);
		tectR.save(tech);
		FOSrepository.save(fos);
		cerReponsitory.save(cer);
	}

	/*
	 * Submit cv random id of user
	 */
	@Override
	public CvInfo submitCv(SubmitCvRequest submitCvRequest) {

		if (cvRepository.existsByCvName(submitCvRequest.getCvname())) {
			return null;
		}

		Random rn = new Random();
		Integer id = rn.nextInt(1000000);

		Boolean run = true;
		while (run) {
			if (!cvRepository.existsByCvNo(id)) {
				run = false;
			}
		}
		addIntoDB(id, submitCvRequest);
		System.out.println("chay qua 2 kiem tra id " + id);

		Optional<CvInfo> check = cvRepository.findById(id);
		if (check.isEmpty()) {
			return null;
		}
		return check.get();
	}

	@Override
	public List<Technical> findAllTech() {

		return (List<Technical>) tectR.findAll();
	}

	/*
	 * Edit cv by id
	 */
	@Override
	public ResponseEntity<?> edit(Integer id, SubmitCvRequest submitCvRequest) {
		// TODO Auto-generated method stub
		Optional<CvInfo> cvid = findById(id);
		if (cvid.isEmpty()) {
			return ResponseEntity.badRequest().body(new ResponseMessage("cv id not exist", new Date()));
		}
		try {
			addIntoDB(id, submitCvRequest);
			
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Can not edit ", new Date()));
		}
		
		return ResponseEntity.badRequest().body(new ResponseMessage("Edit succession", new Date()));
	}

	/*
	 * Delete cv By id
	 */
	@Override
	public void remove(Integer id) {

		tectR.delete(tectR.findByTechnicalNo(id));
		eduRepository.delete(eduRepository.findBySchoolNo(id));
		cerReponsitory.delete(cerReponsitory.findByCertificatNo(id));
		FOSrepository.delete(FOSrepository.findByStudyNo(id));
		cvRepository.delete(cvRepository.findBycvNo(id));

	}

	@Override
	public CvInfo showcvById(Integer id) {
		
		return cvRepository.findBycvNo(id);

	}

}
