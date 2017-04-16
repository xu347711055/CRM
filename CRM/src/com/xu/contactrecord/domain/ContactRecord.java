package com.xu.contactrecord.domain;

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
import com.xu.user.domain.User;

@Entity
public class ContactRecord {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	/**
	 * 联系人姓名
	 */
	@Column(length=30)
	private String contactName;
	/**
	 * 联系人电话
	 */
	@Column(length=30)
	private String telephone;
	/**
	 * 联系日期
	 */
	@Temporal(TemporalType.DATE)
	private Date contactDate;
	/**
	 * 联系记录创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false,updatable=false)
	private Date createTime;
	/**
	 * 更新时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable=false,updatable=false)
	private Date updateTime;
	/**
	 * 记录创建人
	 */
	@ManyToOne
	private User creater;
	/**
	 * 最近更新人
	 */
	@ManyToOne
	private User updater;
	/**
	 * 内容
	 */
	@Column(columnDefinition="TEXT")
	private String content;
	/**
	 * 是否可用(1为可用)
	 */
	private int state;
	@ManyToOne
	private Customer cust;
	
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getContactDate() {
		return contactDate;
	}
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
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
	public User getCreater() {
		return creater;
	}
	public void setCreater(User creater) {
		this.creater = creater;
	}
	public User getUpdater() {
		return updater;
	}
	public void setUpdater(User updater) {
		this.updater = updater;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ContactRecord [id=" + id + ", contactName=" + contactName + ", telephone=" + telephone
				+ ", contactDate=" + contactDate + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", creater=" + creater + ", updater=" + updater + ", content=" + content + ", state=" + state
				+ ", cust=" + cust + "]";
	}
	
}
