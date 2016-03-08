package com.henry.test;

import org.junit.Ignore;
import org.junit.Test;

import com.henry.util.HibernateUtil;
import com.many2one.entity.Group;
import com.many2one.entity.User;

public class HibernateTest {
	@Test
	@Ignore
	//多对一单向
	public void testSave() {
		User user = new User();
		user.setName("Henry");
		User user1 = new User();
		user1.setName("Frank");
		Group group = new Group();
		group.setName("software");
		user.setGroup(group);
		user1.setGroup(group);
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().save(user);
		HibernateUtil.getSession().save(user1);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	@Ignore
	@Test
	public void testGetUser() {
		testSave();
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().get(User.class, 1);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * 要想删除“一”的时候删除“多”，要双向关联。
	 */
	@Test
	public void testDeleteGroup() {
		testSave();
		HibernateUtil.getSession().beginTransaction();
		Group group = (Group)HibernateUtil.getSession().load(Group.class, 1);
		HibernateUtil.getSession().delete(group);
		//User user = (User)HibernateUtil.getSession().load(User.class, 1);
		//user.setGroup(null);
		//HibernateUtil.getSession().delete(user);
		HibernateUtil.getSession().getTransaction().commit();
	}
}
