package com.root.Entity;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
@Table(name = "roles")
public class Role {
	
	private static final Long serialVesionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private ERole name;
	

}
