	# Thymeleaf

> Thymeleaf is a Java-based server-side template engine that can interpret HTML, XML, JavaScript, CSS, and even plain text in standalone and online environments. In addition to generating dynamic material on UIs, it is more powerful than JPS.

> The engine enables backend and frontend devs to work simultaneously on the same screen. It has direct access to Java objects and Spring beans, which it may connect to a user interface. Additionally, it is frequently used while developing web applications with spring MVC. To further understand how Thymeleaf integrates with the Spring framework, let's start with an example.

### project setup
> Here, we'll do a crud operation on the Employee dataset. Therefore, in order to create this, we must add a few dependencies that are stated in bulleted form or in the pom.xml file.

### POM.XML
```xml
'<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https:/
						/maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.6.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>thymeleaf</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>thymeleaf</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```

### application.properties file

```spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/emp
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLInnoDBDialect
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type=TRACE
```

### Employee Pojo

> This is the simple pojo class which is used to store the data of Employee. 

```java
package com.microservice.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
```

### Employee Service interface and EmployeeServiceImpl class

```java 
package com.microservice.service;
import java.util.List;
import com.microservice.modal.Employee;

public interface EmployeeServices {
	List<Employee> getAllEmployee();
	void save(Employee employee);
	Employee getById(Long id);
	void deleteViaId(long id);
}
```

### EmployeeServiceImpl class which implements EmployeeSerivce interface methods

```java
package com.microservice.service;

import com.microservice.modal.Employee;
import com.microservice.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl
	implements EmployeeServices {

	@Autowired private EmployeeRepository empRepo;

	@Override public List<Employee> getAllEmployee()
	{
		return empRepo.findAll();
	}

	@Override public void save(Employee employee)
	{
		empRepo.save(employee);
	}

	@Override public Employee getById(Long id)
	{
		Optional<Employee> optional = empRepo.findById(id);
		Employee employee = null;
		if (optional.isPresent())
			employee = optional.get();
		else
			throw new RuntimeException(
				"Employee not found for id : " + id);
		return employee;
	}

	@Override public void deleteViaId(long id)
	{
		empRepo.deleteById(id);
	}
}
```

### EmployeeRepository Interface
> Here we are using JPA for communicating and saving the object into database.

```java
package com.microservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.modal.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
```
### EmployeeController class
> This is the controller class, which essentially regulates the data flow. Every time data changes, it updates the view and manages the data flow into the model object. As a result, we are now using Thymeleaf to map our object data.

- The request is sent to the viewHomePage() method when a user types localhost:8080/ into their browser. In this method, we fetch the list of employees, add it to a modal with a key-value pair, and then return the index.html page. The key (allemplist) in the index.html page is recognised as a java object, and Thymeleaf iterates over the list to produce dynamic content in accordance with the user-provided template.
 
- When a user selects the Add Employee button, the /addNew command is sent to the addNewEmployee() method. And in this function, we merely construct an empty employee object and send it back to newemployee.html so that the user may fill it up with data. Then, when the user clicks the "Save" button, the /save mapping runs, retrieving the employee object and saving it to the database.
- /showFormForUpdate/{id} – This mapping is for updating the existing employee data.
- /deleteEmployee/{id} – This mapping is for deleting the existing employee data.
```java 
package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.microservice.modal.Employee;
import com.microservice.service.EmployeeServiceImpl;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("allemplist", employeeServiceImpl.getAllEmployee());
		return "index";
	}

	@GetMapping("/addnew")
	public String addNewEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "newemployee";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeServiceImpl.save(employee);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String updateForm(@PathVariable(value = "id") long id, Model model) {
		Employee employee = employeeServiceImpl.getById(id);
		model.addAttribute("employee", employee);
		return "update";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteThroughId(@PathVariable(value = "id") long id) {
		employeeServiceImpl.deleteViaId(id);
		return "redirect:/";

	}
}
```
### index.html
> This page is used to displaying the list of employee. Here we are iterating over the allemplist object which is sent by our controller from viewHomePage() method. 

``` html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Employee</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
<div class="container my-2" align="center">

<h3>Employee List</h3>
<a th:href="@{/addnew}" class="btn btn-primary btn-sm mb-3" >Add Employee</a>
	<table style="width:80%" border="1"
		class = "table table-striped table-responsive-md">
	<thead>
<tr>
	<th>Name</th>
	<th>Email</th>
	<th>Action</th>
</tr>
</thead>
<tbody>
<tr th:each="employee:${allemplist}">
		<td th:text="${employee.name}"></td>
		<td th:text="${employee.email}"></td>
		<td> <a th:href="@{/showFormForUpdate/{id}(id=${employee.id})}"
				class="btn btn-primary">Update</a>
				<a th:href="@{/deleteEmployee/{id}(id=${employee.id})}"
				class="btn btn-danger">Delete</a>
	</td>
</tr>
</tbody>
</table>
</div>
</body>
</html>
```
### newemployee.html
> This page is used to add new employee in the database. Here we simply provide the value in empty fields and click the submit button. Than the data of the employee goes to the saveEmployee() method and save the data into database. 

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Employee Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Employee Management System</h1>
		<hr>
		<h2>Save Employee</h2>

		<form action="#" th:action="@{/save}" th:object="${employee}"
			method="POST">
			<input type="text" th:field="*{name}" placeholder="Employee Name"
				class="form-control mb-4 col-4"> <input type="text"
				th:field="*{email}" placeholder="Employee Email"
				class="form-control mb-4 col-4">

			<button type="submit" class="btn btn-info col-2">Save
				Employee</button>
		</form>

		<hr>

		<a th:href="@{/}"> Back to Employee List</a>
	</div>
</body>
</html>
```
### update.html
> This page is used to update the data of existing employee.

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Employee Management System</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Employee Management System</h1>
		<hr>
		<h2>Update Employee</h2>

		<form action="#" th:action="@{/save}" th:object="${employee}"
			method="POST">
			
			<!-- Add hidden form field to handle update -->
			<input type="hidden" th:field="*{id}" />
			
			<input type="text" th:field="*{Name}" class="form-control mb-4 col-4">
								
				<input type="text" th:field="*{email}" class="form-control mb-4 col-4">
				
				<button type="submit" class="btn btn-info col-2"> Update Employee</button>
		</form>
		
		<hr>
		
		<a th:href = "@{/}"> Back to Employee List</a>
	</div>
</body>
</html>
```



