package com.root.EntityCV;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "field_of_study")
public class FieldOfStudy {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_no")
    private Integer studyNo;

    @Column(name = "study_name")
    private String studyName;
}
