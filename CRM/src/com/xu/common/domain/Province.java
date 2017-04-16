package com.xu.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Province {
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
	
	/*@OneToMany
	private List<City> citys = new ArrayList<>();
*/
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

	@Override
	public String toString() {
		return "Province [id=" + id + ", name=" + name + ", pycode=" + pycode + "]";
	}

	
}
