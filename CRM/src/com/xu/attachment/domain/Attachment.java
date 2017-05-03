package com.xu.attachment.domain;

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
public class Attachment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	/**
	 * 保存到服务器的文件名（UUID）
	 */
	@Column(length=256)
	private String saveName;
	/**
	 * 用户起的文件名
	 */
	@Column(length=128)
	private String fileName;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false,insertable=false)
	private Date createTime;
	@Column(updatable=false,insertable=false)
	private Date updateTime;
	@Column(length=64)
	private String icon;
	@ManyToOne
	private Customer cust;
	@ManyToOne
	private User creator;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	@Override
	public String toString() {
		return "Attachment [id=" + id + ", saveName=" + saveName + ", fileName=" + fileName + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", icon=" + icon  + "]";
	}
	
}
