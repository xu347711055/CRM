package com.xu.role.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xu.user.domain.User;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length=30)
	private String name;
	@Column(length=30)
	private String description;
	
	@Column(insertable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@Column(insertable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@ManyToMany(mappedBy="roles",fetch=FetchType.EAGER)
	private List<User> users = new ArrayList<User>();
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Auth> auths = new ArrayList<>();
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Auth> getAuths() {
		return auths;
	}
	public void setAuths(List<Auth> auths) {
		this.auths = auths;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", users=" + users + ", auths=" + auths + "]";
	}
	
}