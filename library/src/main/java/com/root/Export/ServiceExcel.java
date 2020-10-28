package com.root.Export;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.EntityCV.CvInfo;
import com.root.EntityCV.Repository.SampleRepository;


@Service
public class ServiceExcel {

	@Autowired
	private SampleRepository cvRepository;

	
	public ByteArrayInputStream load() {
		List<CvInfo> cv = cvRepository.findAll();
		ByteArrayInputStream in= DisplayExcel.tutorialsToExcel(cv);
		return in;
	}
}
