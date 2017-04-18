package com.xu.customer.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.xu.common.domain.City;
import com.xu.contact.domain.Contact;
import com.xu.user.domain.User;

@Entity
public class Customer {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**
	 * 客户编号
	 */
	@Column(length=30,nullable=false,unique=true)
	private String cnumber;
	/**
	 * 客户名称
	 */
	@Column(length=80)
	private String cname;
	/**
	 * 客户等级
	 */
	@Column(length=20)
	private String level;
	/**
	 * 区域名称
	 */
	@Column(length=10)
	private String region;
	/**
	 * 客户来源
	 */
	@Column(length=20)
	private String source;
	/**
	 * 所属行业
	 */
	@Column(length=30)
	private String industry;
	/**
	 * 公司规模
	 */
	@Column(length=20)
	private String scale;
	/**
	 * 传真
	 */
	@Column(length=20)
	private String fax;
	/**
	 * 联系地址
	 */
	@Column(length=100)
	private String address;
	/**
	 * 城市
	 */
	@ManyToOne
	private City city;
	/**
	 * 邮政编码
	 */
	@Column(length=10)
	private String postNum;
	/**
	 * 电子邮件
	 */
	@Column(length=20)
	private String email;
	/**
	 * 公司网站
	 */
	@Column(length=30)
	private String website;
	/**
	 * 手机号码
	 */
	@Column(length=12)
	private String telephone;
	/**
	 * 电话1
	 */
	@Column(length=12)
	private String phone1;
	/**
	 * 电话2
	 */
	@Column(length=12)
	private String phone2;
	/**
	 * 下次联系时间
	 */
	@Temporal(TemporalType.DATE)
	private Date contactDate;
	/**
	 * 客户性质
	 */
	@Column(length=20)
	private String custNature;
	/**
	 * 备注
	 */
	@Column(length=256)
	private String remark;
	/**
	 * 经营范围
	 */
	@Column(length=30)
	private String scope;
	/**
	 * 企业性质
	 */
	@Column(length=20)
	private String companyNature;
	/**
	 * 法人代表
	 */
	@Column(length=15)
	private String represent;
	/**
	 * 注册资金
	 */
	@Column
	private int regCapital;
	/**
	 * 开户银行
	 */
	@Column(length=30)
	private String bank;
	/**
	 * 银行账号
	 */
	@Column(length=20)
	private String bankAccount;
	/**
	 * 公司税号
	 */
	@Column(length=25)
	private String taxNum;
	/**
	 * 需求
	 */
	@Column(length=100)
	private String demand1;
	@Column(length=100)
	private String demand2;
	@Column(length=100)
	private String demand3;
	@Column(length=100)
	private String demand4;
	/**
	 * 创建人
	 */
	@ManyToOne
	private User creator;
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable=false,insertable=false)
	private Date createTime;
	/**
	 * 修改人
	 */
	@OneToOne
	private User updater;
	/**
	 * 最近修改时间
	 */
	@Column(updatable=false,insertable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	/**
	 * 所属人
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="customer_owner",
	joinColumns={ @JoinColumn(name="cust")},
	inverseJoinColumns={ @JoinColumn(name = "owner") })	
	private List<User> owner = new ArrayList<>();
	/**
	 * 是否可用(1为可用)
	 */
	private int state;
	@OneToMany(mappedBy="cust")
	private List<Contact> contacts;
	
	@Column(columnDefinition="TEXT")
	private String strategy;
	
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCnumber() {
		return cnumber;
	}
	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public Date getContactDate() {
		return contactDate;
	}
	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
	public String getCustNature() {
		return custNature;
	}
	public void setCustNature(String custNature) {
		this.custNature = custNature;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getCompanyNature() {
		return companyNature;
	}
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}
	public String getRepresent() {
		return represent;
	}
	public void setRepresent(String represent) {
		this.represent = represent;
	}
	public int getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(int regCapital) {
		this.regCapital = regCapital;
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getTaxNum() {
		return taxNum;
	}
	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}
	public String getDemand1() {
		return demand1;
	}
	public void setDemand1(String demand1) {
		this.demand1 = demand1;
	}
	public String getDemand2() {
		return demand2;
	}
	public void setDemand2(String demand2) {
		this.demand2 = demand2;
	}
	public String getDemand3() {
		return demand3;
	}
	public void setDemand3(String demand3) {
		this.demand3 = demand3;
	}
	public String getDemand4() {
		return demand4;
	}
	public void setDemand4(String demand4) {
		this.demand4 = demand4;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public User getUpdater() {
		return updater;
	}
	public void setUpdater(User updater) {
		this.updater = updater;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public List<User> getOwner() {
		return owner;
	}
	public void setOwner(List<User> owner) {
		this.owner = owner;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", cnumber=" + cnumber + ", cname=" + cname + ", level=" + level + ", region="
				+ region + ", source=" + source + ", industry=" + industry + ", scale=" + scale + ", fax=" + fax
				+ ", address=" + address + ", city=" + city + ", postNum=" + postNum + ", email=" + email + ", website="
				+ website + ", telephone=" + telephone + ", phone1=" + phone1 + ", phone2=" + phone2 + ", contactDate="
				+ contactDate + ", custNature=" + custNature + ", remark=" + remark + ", scope=" + scope
				+ ", companyNature=" + companyNature + ", represent=" + represent + ", regCapital=" + regCapital
				+ ", bank=" + bank + ", bankAccount=" + bankAccount + ", taxNum=" + taxNum + ", demand1=" + demand1
				+ ", demand2=" + demand2 + ", demand3=" + demand3 + ", demand4=" + demand4 + ", creator=" + creator
				+ ", createTime=" + createTime + ", updater=" + updater + ", updateTime=" + updateTime + ", owner="
				+ owner + ", state=" + state + "]";
	}
	
	
	
}
