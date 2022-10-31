package com.task.management.system.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	private String name;
	

	
}