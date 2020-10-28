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
@Table(name = "technical")
public class Technical {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technical_no")
    private Integer technicalNo;

    @Column(name = "skill_name")
    private String skillName;

}
