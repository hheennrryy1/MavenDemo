package com.henry.test;

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
		user.setName("Henry");
		User user1 = new User();
		user1.setName("Frank");
		Group group = new Group();
		group.setName("software");
		group.getUsers().add(user);
		group.getUsers().add(user1);
		user.setGroup(group);
		user1.setGroup(group);
		HibernateUtil.getSession().beginTransaction();
		//HibernateUtil.getSession().save(user);
		HibernateUtil.getSession().save(group);//CascadeType.ALL把user也存了
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
	
	@Ignore
	@Test
	public void testLoadGroup() {
		testSaveGroup();
		HibernateUtil.getSession().beginTransaction();
		Group  group = (Group)HibernateUtil.getSession().load(Group.class, 1);
		System.out.println(group);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	@Ignore
	@Test
	public void testUpdateUser() {
		testSaveGroup();
		HibernateUtil.getSession().beginTransaction();
		User user = (User)HibernateUtil.getSession().get(User.class, 1);
		HibernateUtil.getSession().getTransaction().commit();
		
		user.getGroup().setName("hardware");
		user.setName("hheennrryy");
		
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().update(user);//会更新group，因为User里cascade=CascadeType.ALL
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	@Test
	@Ignore
	public void testDeleteUser() {
		testSaveGroup();
		HibernateUtil.getSession().beginTransaction();
		/*
		User user = (User)HibernateUtil.getSession().load(User.class, 1);
		user.setGroup(null);
		HibernateUtil.getSession().delete(user);//如果user的Goup不设null,Group的cascade=CascadeType.ALL把user全删了
		*/
		HibernateUtil.getSession().createQuery("delete from User where id = 1").executeUpdate();//第二种方法
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	@Ignore
	@Test
	public void testDeleteGroup() {
		testSaveGroup();
		HibernateUtil.getSession().beginTransaction();
		Group group = (Group)HibernateUtil.getSession().load(Group.class, 1);
		HibernateUtil.getSession().delete(group);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	
}
