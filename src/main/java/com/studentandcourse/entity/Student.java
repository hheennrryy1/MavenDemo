package com.studentandcourse.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_student")
public class Student {
	private int id;
	private String name;
	Set<Score> set = new HashSet<>();
	//Set<Course> course = new HashSet<>();
	
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
	
	@OneToMany(mappedBy="student")
	public Set<Score> getSet() {
		return set;
	}
	public void setSet(Set<Score> set) {
		this.set = set;
	}
	
	//用两个OneToMany和ManyToOne来代替ManyToMany
	/*
	@ManyToMany
	@JoinTable(name="t_score",
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns=@JoinColumn(name="course_id")
	)
	public Set<Course> getCourse() {
		return course;
	}
	public void setCourse(Set<Course> course) {
		this.course = course;
	}
	 */
}
