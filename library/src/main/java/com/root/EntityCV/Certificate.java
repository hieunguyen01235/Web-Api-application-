package com.root.EntityCV;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
//import lombok.Getter;

@Entity
@Data
@Table(name = "certificate")

public class Certificate {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_no")
    private Integer certificatNo;

    @Column(name = "certificate_name")
    private String certificatName;

    @Column(name = "authority")
    private String authority;

    @Column(name = "license_number")
    private String licenseNumber;
}
