package com.root.EntityCV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.root.EntityCV.Certificate;



public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
	Certificate  findByCertificatNo(Integer certificatNo);
	
}
