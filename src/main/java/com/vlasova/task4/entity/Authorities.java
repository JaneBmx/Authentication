package com.vlasova.task4.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authorities")
public class Authorities {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "authority")
	private String authority;

	@OneToMany(mappedBy = "authorities", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<User> users;

	public Authorities() {
	}

	public Authorities(String authority) {
		this.authority = authority;
	}

	public Authorities(String authority, List<User> users) {
		this.authority = authority;
		this.users = users;
	}

	public void add(User user) {
		if (users == null) users = new ArrayList<>();
		users.add(user);
		user.setAuthorities(this);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}