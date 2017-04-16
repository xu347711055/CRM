package com.xu.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 数据字典
 * @author xu
 *
 */
@Entity
public class Dictionary {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	/**
	 * 备注信息
	 */
	@Column(length=256)
	private String remark;
	/**
	 * 字典索引码
	 */
	@Column(length=15)
	private String code;
	
	@Column(length=45)
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", remark=" + remark + ", code=" + code + ", value=" + value + "]";
	}
	
	
}
