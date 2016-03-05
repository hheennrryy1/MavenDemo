# MavenDemo
第一次使用Maven,顺便练习一下Hibernate.
###Hibernate中使用联合主键的三种方法(第2,3种常用)
1. 将联合主键的字段单独放在一个类中，该类需要实现java.io.Serializable接口并重写equals和hascode，再将该类注解为```@Embeddable```,最后在主类中(该类不包含联合主键类中的字段)保存该联合主键类的一个引用，并生成set和get方法，并将该引用注解为```@Id```
2. 将联合主键的字段单独放在一个类中，该类需要实现java.io.Serializable接口并重写equals和hascode，最后在主类中(该类不包含联合主键类中的字段)保存该联合主键类的一个引用，并生成set和get方法，并将该引用注解为```@EmbeddedId```  
3. 将联合主键的字段单独放在一个类中，该类需要实现java.io.Serializable接口并要重写equals和hashcode.最后在主类中(该类包含联合主键类中的字段)将联合主键字段都注解为```@Id```,并在该类上方将上这样的注解：@IdClass(联合主键类.class)

###Hibernate中Session的get和load方法
当我们使用```session.load()```方法来加载一个对象时，此时并不会发出sql语句，当前得到的这个对象其实是一个代理对象，这个代理对象只保存了实体对象的id值，只有当我们要使用这个对象，得到其它属性时，这个时候才会发出sql语句，从数据库中去查询我们的对象。    
###一对一双向关联
在不设外键的一方使用加入```mappedBy=""```，不然产生两个外键。
###多对一单向关联
使用```@ManytoOne```*不产生中间表*，可以用```@Joincolumn（name=""）```来指定生成外键的名字，外键在多的一方表中产生。
###一对多单向关联
*会产生中间表*，此时可以用```@onetoMany @Joincolumn（name="")```避免产生中间表，并且指定了外键的名字，外键在多的一方表中产生。
###一对多，多对一双向关联
如果不在```@OneToMany```中加```mappedby```属性就会产生中间表，此时通常在```@ManyToOne```的注解下再添上注解```@Joincolumn(name="")```来指定外键的名字。
###多对多单向关联
例子
	```@ManyToMany
	@JoinTable(name="t_s",
				joinColumns={@JoinColumn(name="teacher_id")},
				inverseJoinColumns={@JoinColumn(name="student_id")}
	)
	```  
###多对多双向关联
	在另一边加入```@ManyToMany(mappedBy="")```
