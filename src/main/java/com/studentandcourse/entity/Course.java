package com.studentandcourse.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_course")
public class Course {
	private int id;
	private String name;
	Set<Score> set = new HashSet<>();
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
	
	@OneToMany(mappedBy="course")
	public Set<Score> getSet() {
		return set;
	}
	public void setSet(Set<Score> set) {
		this.set = set;
	}
}
