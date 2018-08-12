# Spring notes

#### XML Spring Configuration

```
In order to tell the Spring Container to create these objects and how to link them together, a configuration must be provided. 
This configuration can be provided using XML files or XML + annotations or Java Configuration classes.
```

In Spring, there are two types of dependency injection specific to XML: via **constructor** and via **setters**

For XML, the class **org.springframework.context.support.ClassPathXmlApplicationContext** is used.

##### Constructor Injection

Constructor injection can be used to define beans when the bean type is a class that has a constructor with arguments defined.

```xml
<beans>
  <bean id="complexBean" class="com.ps.beans.ctr.ComplexBeanImpl">
        <constructor-arg ref=" simpleBean"/>
  </bean>
  
  <bean id= "simpleBean" class="com.ps.beans.SimpleBeanImpl"/>
</beans>
```

Then the class ComplexBeanImpl looks like this:

```java
public class ComplexBeanImpl implements ComplexBean {
    private SimpleBean simpleBean;

    public ComplexBeanImpl(SimpleBean simpleBean) {
        this.simpleBean = simpleBean;
    }

}
```

The **<constructor-arg />** element defines the constructor argument and does so using a number of attributes, but ref is the most common, and it is used to tell the container that the value of this attribute is a reference to another bean.
```xml
<bean id="..." class="...">
        <constructor-arg ref="..."/>
</bean>
```

Another attribute that is commonly used is **value**. This attribute is used when the value to inject is a scalar

```xml
<beans>
  <bean id="complexBean" class="com.ps.beans.ctr.ComplexBeanImpl">
        <constructor-arg ref="simpleBean"/>
        <constructor-arg value="true"/>
  </bean>
</beans>
```
==>
```java
public class ComplexBeanImpl {
      public ComplexBeanImpl(SimpleBean simpleBean, boolean complex) {
         this.simpleBean = simpleBean;
         this.complex = isComplex;
      }
}
```

And also quite useful is the **index** attribute, which should be used when the constructor has more parameters of the same type.

```xml
<beans>
    <bean id="simpleBean0" class="com.ps.beans.SimpleBeanImpl"/>
    <bean id="simpleBean1" class="com.ps.beans.SimpleBeanImpl"/>
    
    <bean id="complexBean2" class="com.ps.beans.ctr.ComplexBean2Impl">
        <constructor-arg ref="simpleBean0" index="0"/>
        <constructor-arg ref="simpleBean1" index="1"/>
    </bean>
</beans>
```
==>
```java
public class ComplexBean2Impl {
    public ComplexBean2Impl(SimpleBean simpleBean1, SimpleBean simpleBean2) {
            this.simpleBean1 = simpleBean1;
            this.simpleBean2 = simpleBean2;
     }
 }
```

Another way to handle constructors with more parameters of the same type is to use the **name** attribute

```xml
<beans>
    <bean id="simpleBean0" class="com.ps.beans.SimpleBeanImpl"/>
    <bean id="simpleBean1" class="com.ps.beans.SimpleBeanImpl"/>
    
    <bean id="complexBean2" class="com.ps.beans.ctr.ComplexBean2Impl">
        <constructor-arg ref="simpleBean0" name="simpleBean1"/>
        <constructor-arg ref="simpleBean1" name="simpleBean2"/>
    </bean>
</beans>
```


If constructor-arg seems to be a long name for a configuration element, do not worry. Spring has introduced a fix for that in version 3.1 the **c-namespace** : xmlns:c="http://www.springframework.org/schema/c"

```xml
<beans>
    <bean id="simpleBean0" class="com.ps.beans.SimpleBeanImpl"/>
    <bean id="simpleBean1" class="com.ps.beans.SimpleBeanImpl"/>
    
    <!-- usage for reference to dependency -->
    <bean id="complexBean0" class="com.ps.beans.ctr.ComplexBeanImpl"
           c:simpleBean-ref="simpleBean0"/>
           
    <!-- usage for primitive type dependency -->
    <bean id="complexBean1" class="com.ps.beans.ctr.ComplexBeanImpl"
          c:simpleBean-ref="simpleBean0" c:complex="true"/>
          
    <!-- usage for index specified references -->
    <bean id="complexBean2" class="com.ps.beans.ctr.ComplexBean2Impl"
     c:_0-ref="simpleBean0" c:_1-ref="simpleBean1" />
     
</beans>
```

``` 
If you are using the name of the constructor parameter to inject the dependency, 
then the attribute definition with c: should match the pattern c:nameConstructorParameter[-ref], 
while if you are using indexes, the attribute definition should match c:_{index}[-ref].
```
