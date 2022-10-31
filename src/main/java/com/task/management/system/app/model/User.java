package com.task.management.system.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;





@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String username;
	private String password;
	private String forgetPasswordToken;
	private boolean enabled;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	//@JoinTable(name ="user_task", joinColumns = @JoinColumn (name="user_id"), inverseJoinColumns = @JoinColumn (name="task_id"))
	private List<Task> task=new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinTable(name ="admin_roles", joinColumns = @JoinColumn (name="admin_id"), inverseJoinColumns = @JoinColumn (name="role_id"))
	private Set<Role> roles=new HashSet<>();


}
