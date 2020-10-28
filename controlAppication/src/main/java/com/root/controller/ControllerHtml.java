package com.root.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.root.EntityCV.CvInfo;
import com.root.EntityCV.Technical;
import com.root.ImplementService.CVImplement;
import com.root.Service.cvService;
import com.root.payout.Request.SubmitCvRequest;

@Controller
@RequestMapping("/api/cv")
public class ControllerHtml {

	@Autowired
	private CVImplement cvService;

//	@PreAuthorize("hasRole('ADMIN')")

	@GetMapping(value = "/showlist")
	public String getEmployees(Model model) {
//		getalluser(limit)

		List<CvInfo> list = cvService.findAllCV();
		System.out.println("===================================================0");

		model.addAttribute("cvlist", list);

//		System.out.print(list);
		return "cvFCT";
	}
//	@PreAuthorize("hasRole('USER')")

	@GetMapping("/submit")
	public String add(Model model) {
		model.addAttribute("cvsubmit", new SubmitCvRequest());
		
		
		
		
		
	    List<String> listskill = new ArrayList<String>();
	    List<Technical> listTech = cvService.findAllTech();
	    for (Technical temp: listTech) {
	    	listskill.add(temp.getSkillName());
		}
		System.out.println(listskill);
		model.addAttribute("listskill",  listskill);
		
		
		return "submitcv";
	}
//	@PreAuthorize("hasRole('USER')")

	@PostMapping("/submit")
	public String addPo(@ModelAttribute("cvsubmit") SubmitCvRequest submitCvRequest, ModelMap modelMap, Model model) {
		modelMap.addAttribute("cvsubmit", submitCvRequest);
		System.out.println(submitCvRequest);
		CvInfo cv = cvService.submitCv(submitCvRequest);
		
		System.out.println("cv ne ========================" + cv);
		List<CvInfo> list = cvService.findAllCV();
		model.addAttribute("cvlist", list);
		return "cvFCT";
	}
}
