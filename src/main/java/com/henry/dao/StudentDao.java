package com.henry.dao;

import org.junit.Ignore;
import org.junit.Test;
import com.henry.entity.Student;
import com.henry.entity.StudentPK;
import com.henry.util.HibernateUtil;
/**
 * 
 * @author henry
 * Session常用的方法
 *
 */
public class StudentDao {
	@Ignore
	@Test
	public void save() {
		StudentPK pk = new StudentPK();
		pk.setId(1);
		pk.setName("henry");
		Student student = new Student();
		student.setPk(pk);
		student.setContent("whatever");
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().save(student);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * 当我们使用session.load()方法来加载一个对象时，此时并不会发出sql语句，
	 * 当前得到的这个对象其实是一个代理对象，这个代理对象只保存了实体对象的id值，
	 * 只有当我们要使用这个对象，得到其它属性时，这个时候才会发出sql语句，从数据库中去查询我们的对象。
	 */
	@Ignore
	@Test
	public void loadAndLoad() {
		StudentPK pk = new StudentPK();
		pk.setId(1);
		pk.setName("henry");
		HibernateUtil.getSession().beginTransaction();
		Student student = (Student) HibernateUtil.getSession().load(Student.class, pk);//后面的1是主键
		//Student student = (Student) HibernateUtil.getSession().get(Student.class, pk);//后面的1是主键
		HibernateUtil.getSession().getTransaction().commit();
		System.out.println(student);
	}
	
	@Ignore
	@Test
	public void update() {
		StudentPK pk = new StudentPK();
		pk.setId(2);
		pk.setName("henry");
		Student student = new Student();
		student.setPk(pk);
		student.setContent("saf");
		HibernateUtil.getSession().beginTransaction();
		HibernateUtil.getSession().saveOrUpdate(student);
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * get和load方法在发出sql语句前都会先从缓存中找,clear方法清除缓存.
	 */
	@Ignore
	@Test
	public void clear(){
		StudentPK pk = new StudentPK();
		pk.setId(1);
		pk.setName("henry");
		HibernateUtil.getSession().beginTransaction();
		Student s = (Student) HibernateUtil.getSession().load(Student.class, pk);
		System.out.println(s.getContent()); //这里才发出sql语句,将对象放在缓存里
		
		HibernateUtil.getSession().clear();
		
		Student s1 = (Student) HibernateUtil.getSession().load(Student.class, pk);
		System.out.println(s1.getContent());
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * 这里setContent()方法执行后,commit时默认进行flush方法(会保持对象与数据库一致),commit会自动更新(注意:这里没update也更新)
	 * 加入flush方法会更新两次
	 */
	@Test
	public void flush() {
		StudentPK pk = new StudentPK();
		pk.setId(1);
		pk.setName("henry");
		HibernateUtil.getSession().beginTransaction();
		//Student s = (Student) HibernateUtil.getSession().load(Student.class, pk);
		Student s = (Student) HibernateUtil.getSession().get(Student.class, pk);//后面的1是主键
		s.setContent("sdfsdfsd");
		//HibernateUtil.getSession().flush();
		//s.setContent("ff");
		
		HibernateUtil.getSession().getTransaction().commit();
	}
}
