package com.henry.test;

import org.junit.Test;

import com.henry.util.HibernateUtil;
import com.studentandcourse.entity.Course;
import com.studentandcourse.entity.Score;
import com.studentandcourse.entity.Student;

public class HibernateTest3 {
	@Test
	public void test() {
		Student student = new Student();
		student.setName("Underwood");
		Course course = new Course();
		course.setName("Java");
		Score score = new Score();
		score.setScore(95);
		score.setStudent(student);
		score.setCourse(course);
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().save(student);
		HibernateUtil.getSession().save(course);
		HibernateUtil.getSession().save(score);
		HibernateUtil.getSession().getTransaction().commit();
	}
}
