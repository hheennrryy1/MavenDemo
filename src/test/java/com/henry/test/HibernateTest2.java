package com.henry.test;

import org.junit.Ignore;
import org.junit.Test;

import com.henry.entity.Husband;
import com.henry.entity.Wife;
import com.henry.util.HibernateUtil;

public class HibernateTest2 {
	/**
	 *@OneToOne单向 
	 *@OneToOne默认没有级联保存，要设置CascadeType.ALL
	 */
	@Test
	@Ignore
	public void testSaveWife() {
		Wife wife = new Wife();
		wife.setName("Soe");
		wife.setContent("Hunt or be hunted");
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().save(wife);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	@Ignore
	@Test
	public void testSaveHusband() {
		Wife wife = new Wife();
		wife.setName("Soe");
		wife.setContent("Hunt or be hunted");		
		Husband husband = new Husband();
		husband.setName("henry");
		husband.setContent("Hunt");
		husband.setWife(wife);
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().save(husband);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	@Test
	public void testDelete() {
		testSaveHusband();
		HibernateUtil.getSession().beginTransaction();
	//	Husband husband = (Husband)HibernateUtil.getSession().load(Husband.class, 1);
	//  HibernateUtil.getSession().delete(husband);
		Wife wife = (Wife)HibernateUtil.getSession().load(Wife.class, 1);
		
		HibernateUtil.getSession().delete(wife);
		HibernateUtil.getSession().getTransaction().commit();		
	}
}
