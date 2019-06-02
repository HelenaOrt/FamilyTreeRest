/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.FamilyTreeRest.FamilyTreeRest.entities;

import com.FamilyTreeRest.FamilyTreeRest.constants.AuthorityName;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private AuthorityName name;

	@ManyToMany(mappedBy = "authorities")
	private List<WebUser> webUsers;

	public Authority(@NotNull AuthorityName name) {
		this.name = name;
	}

	public Authority(List<WebUser> webUsers) {
		this.webUsers = webUsers;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AuthorityName getName() {
		return name;
	}

	public void setName(AuthorityName name) {
		this.name = name;
	}

	public List<WebUser> getWebUsers() {
		return webUsers;
	}

	public void setWebUsers(List<WebUser> webUsers) {
		this.webUsers = webUsers;
	}
}
