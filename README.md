# MavenDemo
第一次使用Maven,顺便练习一下Hibernate.
###Hibernate中使用联合主键的三种方法(第2,3种常用)
1. 将联合主键的字段单独放在一个类中，该类需要实现java.io.Serializable接口并重写equals和hascode，再将该类注解为```@Embeddable```,最后在主类中(该类不包含联合主键类中的字段)保存该联合主键类的一个引用，并生成set和get方法，并将该引用注解为```@Id```
2. 将联合主键的字段单独放在一个类中，该类需要实现java.io.Serializable接口并重写equals和hascode，最后在主类中(该类不包含联合主键类中的字段)保存该联合主键类的一个引用，并生成set和get方法，并将该引用注解为```@EmbeddedId```
3. 将联合主键的字段单独放在一个类中，该类需要实现java.io.Serializable接口并要重写equals和hashcode.最后在主类中(该类包含联合主键类中的字段)将联合主键字段都注解为```@Id```,并在该类上方将上这样的注解：@IdClass(联合主键类.class)
