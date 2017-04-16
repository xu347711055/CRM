package com.xu.contact.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xu.customer.domain.Customer;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length=20,nullable=false)
	private String name;
	/**
	 * 性别：1为男，0为女
	 */
	@Column
	private int sex;

	@Temporal(TemporalType.DATE)
	private Date birthday;
	/**
	 * 传真号
	 */
	@Column(length=20)
	private String fax;
	/**
	 * 部门
	 */
	@Column(length=30)
	private String dept;
	/**
	 * 职务
	 */
	@Column(length=80)
	private String duty;
	/**
	 * 办公室电话
	 */
	@Column(length=15)
	private String officePhone;
	@Column(length=15)
	private String telephone;
	@Column(length=30)
	private String email;
	
	@Column(length=15)
	private String qqNum;
	/**
	 * qq昵称
	 */
	@Column(length=30)
	private String qqName;
	@Column(length=15)
	private String wechatNum;
	@Column(length=30)
	private String wechatName;
	/**
	 * 是否主联系人 1为是 0为不是
	 */
	@Column
	private int mainContact;
	@Column(length=80)
	private String address;
	/**
	 * 爱好
	 */
	@Column(columnDefinition="TEXT")
	private String hobby;
	/**
	 * 忌讳
	 */
	@Column(columnDefinition="TEXT")
	private String hate;
	/**
	 * 备注
	 */
	@Column(columnDefinition="TEXT")
	private String remark;
	/**
	 * 是否可用(1为可用)
	 */
	private int state;
	/**
	 * 对应的客户
	 */
	@ManyToOne
	private Customer cust;
	
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
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
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	public String getQqName() {
		return qqName;
	}
	public void setQqName(String qqName) {
		this.qqName = qqName;
	}
	public String getWechatNum() {
		return wechatNum;
	}
	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}
	public String getWechatName() {
		return wechatName;
	}
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	public int getMainContact() {
		return mainContact;
	}
	public void setMainContact(int mainContact) {
		this.mainContact = mainContact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getHate() {
		return hate;
	}
	public void setHate(String hate) {
		this.hate = hate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", fax=" + fax
				+ ", dept=" + dept + ", duty=" + duty + ", officePhone=" + officePhone + ", telephone=" + telephone
				+ ", email=" + email + ", qqNum=" + qqNum + ", qqName=" + qqName + ", wechatNum=" + wechatNum
				+ ", wechatName=" + wechatName + ", mainContact=" + mainContact + ", address=" + address + ", hobby="
				+ hobby + ", hate=" + hate + ", remark=" + remark + ", state=" + state + ", cust=" + cust + "]";
	}
	
}
