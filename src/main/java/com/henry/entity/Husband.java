package com.henry.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Husband {
	private int id;
	private String name;
	private String content;
	private Wife wife;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@OneToOne //һ��һ�����������
	@JoinColumn(name="wifeid") //��������� Ĭ��Ϊwife_id
	public Wife getWife() {
		return wife;
	}
	public String getContent() {
		return content;
	}
	public String getName() {
		return name;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWife(Wife wife) {
		this.wife = wife;
	}
	
}
