package com.xu.dept.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.xu.user.domain.User;
@Entity
public class Department {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	/**
	 * 部门名称
	 */
	@Column(length=30,nullable=false)
	private String name;
	/**
	 * 部门负责人
	 */
	@Column(length=80)
	private String principal;

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
	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", principal=" + principal + "]";
	}
	
}
