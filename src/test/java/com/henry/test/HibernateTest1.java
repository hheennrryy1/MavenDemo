package com.henry.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.henry.util.HibernateUtil;
import com.many2one.one2many.entity.Group;
import com.many2one.one2many.entity.User;

/**这是测试一对多，多对一的双向关联
 * 双向关系在程序中要设定双向关联
 * 双向关系要设定mappedBy
 * @author henry
 *
 */
public class HibernateTest1 {
	@Ignore
	@Test
	public void testSaveGroup() {
		User user = new User();
		user.setName("henry");
		Group group = new Group();
		group.setName("software");
		group.getUsers().add(user);
		user.setGroup(group);
		HibernateUtil.getSession().beginTransaction();
//		HibernateUtil.getSession().save(user);
		HibernateUtil.getSession().save(group);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * 不设cascade也取group("一"那方)
	 * 默认为FetchType.EAGER
	 * FecthType.LAZY才不取
	 */
	@Ignore
	@Test
	public void testGetUser() {
		testSaveGroup();
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().get(User.class, 1);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * 默认为Fetch.LAZY，不取Users
	 */
	@Test
	@Ignore
	public void testGetGroup() {
		testSaveGroup();
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().get(Group.class, 1);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	@Ignore
	@Test
	public void testLoadUser() {
		testSaveGroup();
		HibernateUtil.getSession().beginTransaction();
		User user = (User)HibernateUtil.getSession().load(User.class, 1);
		System.out.println(user);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	@Test
	public void testLoadGroup() {
		testSaveGroup();
		HibernateUtil.getSession().beginTransaction();
		Group  group = (Group)HibernateUtil.getSession().load(Group.class, 1);
		System.out.println(group);
		HibernateUtil.getSession().getTransaction().commit();
	}
}
