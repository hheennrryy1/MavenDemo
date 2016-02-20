package com.henry.dao;

import org.junit.Test;

import com.henry.entity.Student;
import com.henry.util.HibernateUtil;

public class StudentDao {
	@Test
	public void save() {
		Student student = new Student();
		student.setName("henry");
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().save(student);
		HibernateUtil.getSession().getTransaction().commit();
	}
}
