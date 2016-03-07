package com.henry.test;

import org.junit.Test;

import com.henry.util.HibernateUtil;
import com.many2one.entity.Group;
import com.many2one.entity.User;

public class HibernateTest {
	@Test
	//∂‡∂‘“ª
	public void testMany2One() {
		User user = new User();
		user.setName("henry");
		Group group = new Group();
		group.setName("software");
		user.setGroup(group);
		HibernateUtil.getSession().beginTransaction();
	//	HibernateUtil.getSession().save(group);
		HibernateUtil.getSession().save(user);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	public void testMany2OneBi() {
		User user = new User();
		user.setName("henry");
		Group group = new Group();
		group.setName("software");
		user.setGroup(group);
		HibernateUtil.getSession().beginTransaction();
	//	HibernateUtil.getSession().save(group);
		HibernateUtil.getSession().save(user);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
}
