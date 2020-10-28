package com.root.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import com.root.EntityCV.CvInfo;
import com.root.Export.ServiceExcel;
import com.root.ImplementService.CVImplement;

import com.root.payout.Request.SubmitCvRequest;
import com.root.payout.messeger.ResponseMessage;
import com.root.payout.messeger.ResponseMessageWithData;

@RestController
@RequestMapping("/api/cv")
public class cvControl {

	@Autowired
	private CVImplement cvService;

	/*
	 * 
	 * 
	 * Submit json cv
	 * 
	 * 
	 */
//	@PreAuthorize("hasRole('USER')")
	@PostMapping("/submitCv")
	public ResponseEntity<?> submitCvForUser(@RequestBody SubmitCvRequest submitCvRequest) {

		System.out.println("chaty qua hhhhshdhsdhsdkhsdkjsh1");

		CvInfo cv = cvService.submitCv(submitCvRequest);

		if (cv == null) {
			return ResponseEntity.badRequest().body(new ResponseMessage(
					new SQLIntegrityConstraintViolationException("cv_ name is use").getMessage(), new Date()));
		}
		return ResponseEntity.ok(new ResponseMessage("Submit successfully" + cv.getCvName(), new Date()));
	}

	/*
	 * 
	 * Show json cv
	 * 
	 */
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/cvlist")
	public ResponseEntity<List<CvInfo>> findAllCv() {
		List<CvInfo> cvlist = cvService.findAllCV();

		if (cvlist.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<>(cvlist, HttpStatus.OK);
	}

	/*
	 * 
	 * Delete cv
	 * 
	 * 
	 */
//	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
		try {
			cvService.remove(id);
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Delete not succession", new Date()));
		}

		return ResponseEntity.badRequest().body(new ResponseMessage("Delete succession "+ id, new Date()));
	}

	
	/*
	 * 
	 * Show cv by id 
	 * 
	 * 
	 */
//	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> showCvById(@PathVariable("id") Integer id) {
		
		try {
			CvInfo cva = cvService.showcvById(id);
			
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Not Find CV id ", new Date()));
		}
		CvInfo cva = cvService.showcvById(id);
		return ResponseEntity.badRequest().body(new ResponseMessageWithData("Cv Info", new Date(), cva ));
	}
	
	/*
	 * 
	 * Edit cv by admin
	 * 
	 */
//	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> edit(@PathVariable("id") Integer id, @RequestBody SubmitCvRequest submitCvRequest) {

		return cvService.edit(id, submitCvRequest);
	}

	/*
	 * 
	 * ADD new cv new id
	 * 
	 */
//	@PreAuthorize("hasRole('ADMIN')")
	
	@RequestMapping(value = "/addcv", method = RequestMethod.POST)
	public ResponseEntity<?> addCv(@RequestBody SubmitCvRequest submitCvRequest) {
		try {
			cvService.submitCv(submitCvRequest);
			
		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(new ResponseMessage("Cv_name exists", new Date()));
		}

		return ResponseEntity.badRequest().body(new ResponseMessage("Add succession", new Date()));
	}
	/*
	 * 
	 * Download Excel 
	 * 
	 */
	@Autowired
	private ServiceExcel serviceExcel;
	
	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {
	    String filename = "CvList.xlsx";
	    InputStreamResource file = new InputStreamResource(serviceExcel.load());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
	        .body(file);
	  }
	
}
