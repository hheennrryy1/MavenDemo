package com.henry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Wife {
	private int id;
	private String name;
	private String content;
	//private Husband husband;
	
	public String getContent() {
		return content;
	}
	
	//@OneToOne//会有两个外键
	//@OneToOne(mappedBy="wife")//双向外键关联必设，不然会有两个外键 意思是Husband类的wife属性
	/*
	public Husband getHusband() {
		return husband;
	}
	*/
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setContent(String content) {
		this.content = content;
	}
	/*
	public void setHusband(Husband husband) {
		this.husband = husband;
	}
	*/
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
