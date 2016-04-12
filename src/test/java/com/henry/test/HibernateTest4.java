package com.henry.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;

import com.henry.OneAndN.Category;
import com.henry.OneAndN.Topic;
import com.henry.util.HibernateUtil;

public class HibernateTest4 {
	
	@Ignore
	@Test	
	public void test() {
		HibernateUtil.getSession().beginTransaction();
		//List<Topic> topics = HibernateUtil.getSession().createQuery("from Topic").list();
		List<Topic> topics = HibernateUtil.getSession().createQuery("from Topic t left join fetch t.category").list();
		System.out.println(topics.get(0).getName());
		System.out.println(topics.get(0).getCategory().getName());
		HibernateUtil.getSession().getTransaction().commit();
	}
	/**
	 * 测试criteria
	 */
	@Test
	@Ignore
	public void test1() {
		HibernateUtil.getSession().beginTransaction();
		Criteria c = HibernateUtil.getSession().createCriteria(Topic.class)
				.add(Restrictions.eq("id", 1));
		for(Object o : c.list()) {
			Topic t = (Topic) o;
			System.out.println(t.getId() + "|" + t.getContent());
		}
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * 测试二级缓存
	 */
	@Test
	@Ignore
	public void testCache() {
		HibernateUtil.getSession().beginTransaction();
		Category c = (Category) HibernateUtil.getSession().load(Category.class, 1);
		System.out.println(c.getName());
		HibernateUtil.getSession().getTransaction().commit();
		
		//在二级缓存中找到
		HibernateUtil.getSession().beginTransaction();
		Category c1 = (Category) HibernateUtil.getSession().load(Category.class, 1);
		System.out.println(c1.getName());
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * 测试查询缓存
	 */
	@Test
	@Ignore
	public void tesQueryCache() {
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().getTransaction().commit();
		List<Category> list = HibernateUtil.getSession().createQuery("from Category where id = 1").setCacheable(true).list();
		//在查询缓存中找到
		HibernateUtil.getSession().beginTransaction();
		List<Category> list1 =  HibernateUtil.getSession().createQuery("from Category where id = 1").setCacheable(true).list();
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * 悲观锁
	 */
	@Test
	@Ignore
	public void testPessimisticLock() {
		HibernateUtil.getSession().beginTransaction();
	//	HibernateUtil.getSession().load(Category.class, 1, LockOptions.UPGRADE);
		List<Category> list = HibernateUtil.getSession().createQuery("from Category where id = 1").setLockOptions(LockOptions.UPGRADE).setCacheable(true).list();
		HibernateUtil.getSession().getTransaction().commit();		
	}
	
	/**
	 * 乐观锁
	 */
	@Test
	public void testOptimisticLock() {
		Session s1 = HibernateUtil.getSession();
		Session s2 = HibernateUtil.getSession();
		
		s1.beginTransaction();
		Category c1 = (Category) s1.load(Category.class, 1);
		
		s2.beginTransaction();
		Category c2 = (Category) s2.load(Category.class, 1);
		
		c1.setName("frank");
		c2.setName("underwood");
		
		s1.getTransaction().commit();//version变为1
		System.out.println(c1.getVersion());
		
		s2.getTransaction().commit();
		System.out.println(c2.getVersion());
		
		s1.close();
		s2.close();
	}
}
