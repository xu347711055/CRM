package com.xu.user.domain;

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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xu.customer.domain.Customer;
import com.xu.dept.domain.Department;
import com.xu.role.domain.Role;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(length=20,unique=true,nullable=false)
	private String account;
	
	@Column(length=36,nullable=false)
	private String password;
	/**
	 * 用户姓名
	 */
	@Column(length=15,nullable=false)
	private String name;
	@Column(length=15)
	private String telephone;
	@Column(length=30)
	private String email;
	/**
	 * 是否管理员(1为是，0为不是)
	 */
	private int admin;
	/**
	 * 是否可用（1为可用账号，0为封锁账号）
	 */
	@Column(columnDefinition="tinyint",insertable=false)
	private int status;
	/**
	 * 拥有的客户
	 */
	@ManyToMany(mappedBy="owner")
	private List<Customer> cust = new ArrayList<>();
	/**
	 * 所属部门
	 */
	@ManyToOne
	private Department dept;
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false,updatable=false)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false,updatable=false)
	private Date updateTime;
	
	@ManyToOne 
	private Role role;
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public List<Customer> getCust() {
		return cust;
	}
	public void setCust(List<Customer> cust) {
		this.cust = cust;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
