package com.henry.dao;

import org.junit.Ignore;
import org.junit.Test;
import com.henry.entity.Student;
import com.henry.entity.StudentPK;
import com.henry.util.HibernateUtil;
/**
 * 
 * @author henry
 * Session���õķ���
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
	 * ������ʹ��session.load()����������һ������ʱ����ʱ�����ᷢ��sql��䣬
	 * ��ǰ�õ������������ʵ��һ�������������������ֻ������ʵ������idֵ��
	 * ֻ�е�����Ҫʹ��������󣬵õ���������ʱ�����ʱ��Żᷢ��sql��䣬�����ݿ���ȥ��ѯ���ǵĶ���
	 */
	@Ignore
	@Test
	public void loadAndLoad() {
		StudentPK pk = new StudentPK();
		pk.setId(1);
		pk.setName("henry");
		HibernateUtil.getSession().beginTransaction();
		Student student = (Student) HibernateUtil.getSession().load(Student.class, pk);//�����1������
		//Student student = (Student) HibernateUtil.getSession().get(Student.class, pk);//�����1������
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
	 * get��load�����ڷ���sql���ǰ�����ȴӻ�������,clear�����������.
	 */
	@Ignore
	@Test
	public void clear(){
		StudentPK pk = new StudentPK();
		pk.setId(1);
		pk.setName("henry");
		HibernateUtil.getSession().beginTransaction();
		Student s = (Student) HibernateUtil.getSession().load(Student.class, pk);
		System.out.println(s.getContent()); //����ŷ���sql���,��������ڻ�����
		
		HibernateUtil.getSession().clear();
		
		Student s1 = (Student) HibernateUtil.getSession().load(Student.class, pk);
		System.out.println(s1.getContent());
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	/**
	 * ����setContent()����ִ�к�,commitʱĬ�Ͻ���flush����(�ᱣ�ֶ��������ݿ�һ��),commit���Զ�����(ע��:����ûupdateҲ����)
	 * ����flush�������������
	 */
	@Test
	public void flush() {
		StudentPK pk = new StudentPK();
		pk.setId(1);
		pk.setName("henry");
		HibernateUtil.getSession().beginTransaction();
		//Student s = (Student) HibernateUtil.getSession().load(Student.class, pk);
		Student s = (Student) HibernateUtil.getSession().get(Student.class, pk);//�����1������
		s.setContent("sdfsdfsd");
		//HibernateUtil.getSession().flush();
		//s.setContent("ff");
		
		HibernateUtil.getSession().getTransaction().commit();
	}
}
