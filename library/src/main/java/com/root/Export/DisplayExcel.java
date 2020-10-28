package com.root.Export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.root.EntityCV.Certificate;
import com.root.EntityCV.CvInfo;
import com.root.EntityCV.Education;
import com.root.EntityCV.Technical;

public class DisplayExcel {

	public static String TPYE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	static String[] heads = { "Cv_No", "Cv Name", "Sex", "education", "Phone Number", "address", "img_path", "About_me",
			"Degeree", "School Name", "Study Name", "Skill Name", "Certificate Name", "AuThority ", "licenseNumber",
			"Year Start", "Year End", "IssuseDate", "Experence Year", "Experence Month", "TechnicalLevel" };
	static String SHEET = "cvinfone";

	public static ByteArrayInputStream tutorialsToExcel(List<CvInfo> cvInfo) {
		try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);

			Row head = sheet.createRow(0);
			for (int i = 0; i < heads.length; i++) {
				Cell cell = head.createCell(i);
				cell.setCellValue(heads[i]);
			}

			int i = 1;
			for (CvInfo cvin : cvInfo) {
				Row row = sheet.createRow(i++);
				row.createCell(1).setCellValue(cvin.getCvName());
				row.createCell(0).setCellValue(cvin.getCvNo());
				row.createCell(4).setCellValue(cvin.getPhoneNum());
				row.createCell(5).setCellValue(cvin.getAddress());
				row.createCell(6).setCellValue(cvin.getImgPath());
				row.createCell(7).setCellValue(cvin.getAboutMe());
				row.createCell(8).setCellValue(cvin.getDegree());
				row.createCell(15).setCellValue(cvin.getYearStart());
				row.createCell(16).setCellValue(cvin.getYearEnd());
				row.createCell(17).setCellValue(cvin.getIssuseDate());
				row.createCell(18).setCellValue(cvin.getExperenceYear());
				row.createCell(19).setCellValue(cvin.getExperence_month());
				row.createCell(20).setCellValue(cvin.getTechnicalLevel());

				String sex = "Nu";
				if (null != cvin.getSex() && cvin.getSex() == 0) {
					sex = "Nam";
				}
				row.createCell(2).setCellValue(sex);

				Set<Education> ed = cvin.getEducation();
				for (Education value : ed) {
					if (null != value) {
						row.createCell(9).setCellValue(value.getSchoolName());
					}
				}
				Set<Technical> te = cvin.getTechnical();
				for (Technical value : te) {
					if (null != value) {
						row.createCell(11).setCellValue(value.getSkillName());
					}
				}

				Set<Certificate> cer = cvin.getCertificate();
				for (Certificate value : cer) {
					row.createCell(13).setCellValue(value.getAuthority());
					row.createCell(14).setCellValue(value.getLicenseNumber());
					row.createCell(12).setCellValue(value.getCertificatName());
				}

			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("hhh" + e.getMessage());
		}

	}
}
