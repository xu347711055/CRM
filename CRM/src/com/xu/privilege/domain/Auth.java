package com.xu.privilege.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.xu.role.domain.Role;
@Entity
public class Auth {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	/**
	 * 权限名称
	 */
	@Column
	private String name;
	/**
	 * 状态
	 */
	@Column
	private int state;
	/**
	 * 权限可访问的路径
	 */
	@Column(length=100,nullable=false)
	private String accessPath;
	/**
	 * 部门
	 */
	@ManyToMany(mappedBy="auths")
	private List<Role> roles = new ArrayList<>();
	
	private int sort;
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccessPath() {
		return accessPath;
	}
	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
