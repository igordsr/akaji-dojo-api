package br.com.akaji.dojo.security;

import java.util.List;

public class GroupAuthority {

	private List<Group> groups;
	private String authority;

	public GroupAuthority() {
		// TODO Auto-generated constructor stub
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
