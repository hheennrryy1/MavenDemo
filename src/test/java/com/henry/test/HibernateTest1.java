package com.henry.test;

import org.junit.Ignore;
import org.junit.Test;

import com.henry.util.HibernateUtil;
import com.many2one.one2many.entity.Group;
import com.many2one.one2many.entity.User;

/**���ǲ���һ�Զ࣬���һ��˫�����
 * ˫���ϵ�ڳ�����Ҫ�趨˫�����
 * ˫���ϵҪ�趨mappedBy
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
		HibernateUtil.getSession().save(group);//CascadeType.ALL��userҲ����
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * ����cascadeҲȡgroup("һ"�Ƿ�)
	 * Ĭ��ΪFetchType.EAGER
	 * FecthType.LAZY�Ų�ȡ
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
	 * Ĭ��ΪFetch.LAZY����ȡUsers
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
		HibernateUtil.getSession().update(user);//�����group����ΪUser��cascade=CascadeType.ALL
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
		HibernateUtil.getSession().delete(user);//���user��Goup����null,Group��cascade=CascadeType.ALL��userȫɾ��
		*/
		HibernateUtil.getSession().createQuery("delete from User where id = 1").executeUpdate();//�ڶ��ַ���
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
