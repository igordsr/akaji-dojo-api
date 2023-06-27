package br.com.akaji.dojo.security;

import java.util.List;

public class GroupMember {

	private Integer id;
	private List<Group> groups;

	public GroupMember() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}
