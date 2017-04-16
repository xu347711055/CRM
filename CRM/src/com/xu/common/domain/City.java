package com.xu.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class City {
	@Id
	@GeneratedValue
	private int id;
	@Column(length=18)
	private String name;
	/**
	 * 拼音码
	 */
	@Column(length=8)
	private String pycode;
	@Column(columnDefinition="CHAR")
	private String postcode;
	@Column(columnDefinition="CHAR")
	private String areacode;
	@ManyToOne()
	@JoinColumn(name="pid")
	private Province province;
	
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
	public String getPycode() {
		return pycode;
	}
	public void setPycode(String pycode) {
		this.pycode = pycode;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", pycode=" + pycode + ", postcode=" + postcode + ", areacode="
				+ areacode + ", province=" + province + "]";
	}
	
	
}
