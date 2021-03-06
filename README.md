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
生成中间表```JoinColumns参考当前对象的id```，```inverseJoinColumns是参考另一个对象的id```
	```@ManyToMany
	@JoinTable(name="t_s",
				joinColumns={@JoinColumn(name="teacher_id")},
				inverseJoinColumns={@JoinColumn(name="student_id")}
	)
	```  
###多对多双向关联
在另一边加入``@ManyToMany(mappedBy="")``
###关于CRUD
1. get和load的是many的一方，那么默认也取one的一方。即默认@ManyToOne(fetch=FetchType.EAGER)
2. get和load的是one的一方，那么默认一开始不取many的一方，当你用到的时候才取。即默认@OneToMany(fetch=FetchType.LAZY)
3. cascade管的是增删改不包括查。

###1+N问题
举例，多个主题（Topic）属于一个帖子（Category），一个帖子含有多个主题。当只需要查询Topic时不要查询Category时，如果@ManyToOne的属性fetch=FetchType.EAGER,这时查询所有Topic时，每查询一个Topic就会多产生一个SQL语句查询相关的Category表的数据，这样要是有N条Topic数据，就会产生1+N条SQL语句。同样的在@OneToMany的情况下，要是在Many方设置fetch=FetchType.EAGER，同样也会产生1+N的问题。
####解决方案
1. fetch=FetchType.LAZY，设为懒加载
2. @BatchSize（size=5）代表一次取5条数据，这样取5条数据只要发出一条SQL语句，注意是用在被关联类上的（没什么用,不建议用）
3. 左外连接检索 join fetch(Criteria 查询默认就是join fetch)

###二级缓存
 二级缓存只能缓存整个对象，不能缓存对象属性，而且对load/get方法、list/iterate方法的在使用上跟一级缓存一样。
二级缓存是SessionFactory级的缓存，它允许多个Session对象之间共用。
在配置文件上加入：
```
<property name="hibernate.cache.use_second_level_cache">true</property>
<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
```
```
在类上加@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
```
###查询缓存(基于二级缓存)
查询缓存是专为Query的list方法设计的。对于iterate()方法，无论是查询对象属性还是对象本身，查询缓存用与不用都没有区别！
在配置文件上加入：
```<property name="hibernate.cache.use_query_cache">true</property>```
###悲观锁
对数据被外界（包括本系统当前的其它事务和来自外部系统的事务处理）修改持保守态度，通过数据库提供的锁机制实现
最常用的，是对查询进行加锁（LockMode.UPGRADE和LockMode.UPGRADE_NOWAIT）
```
HibernateUtil.getSession().load(Category.class, 1, LockOptions.UPGRADE);
```
这次事务提交前，外界无法修改这些记录，事务提交时会释放事务过程中的锁。
###乐观锁
在数据库中增加version列，用来记录每行数据的版本。在类的getVersion()方法上加上```@Version```
通过为数据库表增加一个 “version” 字段来实现。读取出数据时，将此版本号一同读出，之后更新时，对此版本号加一。此时，将提交数据的版本数据与数据库表对应记录的当前版本信息进行比对，如果提交的数据版本号大于数据库表当前版本号，则予以更新，否则认为是过期数据。
