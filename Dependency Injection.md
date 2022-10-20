# What is Dependency Injection:

> The primary feature offered by Spring IOC is Dependency Injection (Inversion of Control). Dependencies must be injected by the Spring-Core module using either Constructor or Setter methods. The Inversion of Control design approach places a strong emphasis on maintaining the independence of Java classes from one another and relies on the container to relieve them of object creation and management.
> These classes, managed by Spring, must adhere to the standard definition of Java-Bean. Dependency Injection in Spring also ensures loose-coupling between the classes.
Need for Dependency Injection:
> Class One is said to be reliant on class Two if, for example, class One requires an object from class Two in order to create or use a method. Even though it might appear to be fine to depend on one module for another, doing so could actually cause a lot of issues, including system failure. As a result, these dependencies must be avoided.

> Such dependencies are resolved using Dependency Injection in Spring IOC, making the code more testable and reusable. By creating interfaces for common functionality, loose coupling between classes is made possible, and the injector will create the necessary implementation-specific objects. The container creates objects in accordance with the configurations that the developer has specified.
### Types of Spring Dependency Injection: 

> There are two types of Spring Dependency Injection. They are: 
### 1. Setter Dependency Injection (SDI):
###### The less complicated of the two DI approaches is this one. Setter and/or getter methods will be used in this to inject the DI. Now, the bean-configuration file is used to change the DI to SDI in the bean. In the bean-config file, the property that needs to be set with the SDI is specified beneath the property> tag.
Example: Let's assume that class GFG utilises SDI and has the geeks attribute set. Below is the code for it.
```java
package com.geeksforgeeks.org;
import com.geeksforgeeks.org.IGeek;
public class GFG {

	// The object of the interface IGeek
	IGeek geek;

	// Setter method for property geek
	public void setGeek(IGeek geek)
	{
		this.geek = geek;
	}
}
```
## Setting the SDI in the bean-config file: 

```xml
<beans
xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

	<bean id="GFG" class="com.geeksforgeeks.org.GFG">
		<property name="geek">
			<ref bean="CsvGFG" />
		</property>
	</bean>
	
<bean id="CsvGFG" class="com.geeksforgeeks.org.impl.CsvGFG" />
<bean id="JsonGFG" class="com.geeksforgeeks.org.impl.JsonGFG" />
		
</beans>
```
### This injects the ‘CsvGFG’ bean into the ‘GFG’ object with the help of a setter method (‘setGeek’)

### 2. Constructor Dependency Injection (CDI):
###### The constructors used in this will be used to inject the DI. Currently, using the bean-configuration file, the DI is set as CDI in beans. For this, the property that will be set via the CDI is stated in the bean-config file's constructor-arg> tag.
- Example: Let us take the same example as of SDI 
```java
package com.geeksforgeeks.org;
import com.geeksforgeeks.org.IGeek;
public class GFG {

	// The object of the interface IGeek
	IGeek geek;

	// Constructor to set the CDI
	GFG(IGeek geek)
	{
		this.geek = geek;
	}
}
```
- Setting the CDI in the bean-config file: 

``` xml
<beans
xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

	<bean id="GFG" class="com.geeksforgeeks.org.GFG">
		<constructor-arg>
			<bean class="com.geeksforgeeks.org.impl.CsvGFG" />
		</constructor-arg>
	</bean>
	
<bean id="CsvGFG" class="com.geeksforgeeks.org.impl.CsvGFG" />
<bean id="JsonGFG" class="com.geeksforgeeks.org.impl.JsonGFG" />
		
</beans>
```
- This injects the ‘CsvGFG’ bean into the ‘GFG’ object with the help of a constructor.

Example of Spring DI:

- We have used three classes and an interface as beans to exemplify the concepts of CDI and SDI. They are Vehicle, ToyotaEngine, Tyres classes and IEngine interface respectively. 
- From our example, we can see that class Vehicle depends on the implementation of the Engine, which is an interface. (So, basically, a Vehicle manufacturer wants a standard Engine which complies to Indian emission norms.) Class ToyotaEngine implements the interface and its reference is provided in the bean-configuration file mapped to one of Vehicle class’s properties.
-In the Vehicle class, we invoke the application context and bean instantiation is executed. Two objects of class Vehicle are instantiated. ‘obj1’ is instantiated via bean with name InjectwithConstructor. The bean name could be located in the bean configuration file. Similarly ‘obj2’ is instantiated via bean with name InjectwithSetter. 
- It can be observed that ‘obj1’ is injected via the constructor and ‘obj2’ uses setter injection.

- In the bean configuration file below, we have used two Vehicle beans’ declarations.

- njectwithConstructor bean makes use of element constructor-arg, with attributes name and ref. ‘Name’ attribute correlates with the constructor argument name given in the Vehicle class definition. And ‘ref’ attribute points to the bean reference which can be used for injecting.

- InjectwithSetter makes use of property element to provide the ‘name’ of the property and the ‘value’ for the property. In place of value attribute ‘ref’ can be used to denote a reference to a bean.

- In the configuration details, we are injecting ToyotaBean reference into the IEngine reference in Vehicle class constructor-arg, where IEngine is an interface and needs an implementing class reference for bean injection.

- We have used two separate bean references for Tyres class, to inject via setter and constructor respectively. We can observe that ‘tyre1Bean’ and ‘tyre2Bean’ are initialized with String literal values for each of the properties.

 



