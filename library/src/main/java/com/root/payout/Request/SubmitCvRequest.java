package com.root.payout.Request;

import java.util.Set;

import com.root.EntityCV.CvInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitCvRequest {
	
	
//	private Set<CvInfo> cvIn;
	private String cvname;

	private String gender;
	
	private String phoneNum;
	
	private String address;
	
	private String img_path;
	
	private String aboutMe;
	
	private String degree;
	
	//f o s  - edu - tech 
	
	private String studyname;
	
	private String schoolname;
	
	private String skillname;
	
	//certificate table
	
	private String certificatName;//chung chi
	
	private  String authority; //quyen
	
	private String licenseNumber; //so giay phep
	
	private Integer technicalLevel;
	
	private String yearStart;
	
	private String yearEnd;
	
	private Integer completeDegree;
	
	private String issuseDate;
	
    private String experenceYear;
	
	private String experence_month;
	
	
}
