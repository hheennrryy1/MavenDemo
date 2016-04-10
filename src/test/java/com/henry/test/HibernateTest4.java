package com.henry.test;

import java.util.List;

import org.junit.Test;

import com.henry.OneAndN.Topic;
import com.henry.util.HibernateUtil;

public class HibernateTest4 {
	
	@Test	
	public void test() {
		HibernateUtil.getSession().beginTransaction();
		//List<Topic> topics = HibernateUtil.getSession().createQuery("from Topic").list();
		List<Topic> topics = HibernateUtil.getSession().createQuery("from Topic t left join fetch t.category").list();
		System.out.println(topics.get(0).getName());
		System.out.println(topics.get(0).getCategory().getName());
		HibernateUtil.getSession().getTransaction().commit();
	}
}
