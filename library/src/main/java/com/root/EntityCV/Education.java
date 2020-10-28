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
@Table(name = "education")
public class Education {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_no")
    private Integer schoolNo;

    @Column(name = "school_name")
    private String schoolName;

}
