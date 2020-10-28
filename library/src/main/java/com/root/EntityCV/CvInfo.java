package com.root.EntityCV;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name = "cv_info")
public class CvInfo {

    @Id
    
    @Column(name = "cv_no")
    private Integer cvNo;

    @Column(name = "cv_name")
    private String cvName;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "address")
    private String address;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "degree")
    private String degree;

    @OneToMany
    @JoinColumn(name = "study_no", insertable = false, updatable = false)
    private Set<FieldOfStudy> fieldOfStudy = new HashSet<FieldOfStudy>();

    @OneToMany
    @JoinColumn(name = "school_no", insertable = false, updatable = false)
    private Set<Education> education;

    @OneToMany
    @JoinColumn(name = "certificate_no", insertable = false, updatable = false)
    private Set<Certificate> certificate;

    @OneToMany
    @JoinColumn(name = "technical_no", insertable = false, updatable = false)
    private Set<Technical> technical;

    @Column(name = "year_start")
    private String yearStart;

    @Column(name = "year_end")
    private String yearEnd;

    @Column(name = "complete_degree")
    private Integer completeDegree;

    @Column(name = "issuse_date")
    private String issuseDate;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "experence_year")
    private String experenceYear;

    @Column(name = "experence_month")
    private String experence_month;

    @Column(name = "technical_level")
    private Integer technicalLevel;

}
