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
	
	//@OneToOne//�����������
	//@OneToOne(mappedBy="wife")//˫������������裬��Ȼ����������� ��˼��Husband���wife����
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
