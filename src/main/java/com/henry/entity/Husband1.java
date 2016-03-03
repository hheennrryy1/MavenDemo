package com.henry.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Husband1 {
	private int id;
	private String name;
	private Wife1 wife;
	
	@Id
	@GeneratedValue
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
	
	@Embedded   //组件映射 把wife放在同一张表里
	public Wife1 getWife1() {
		return wife;
	}
	public void setWife1(Wife1 wife) {
		this.wife = wife;
	}
	
}
