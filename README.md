Erudio API with SpringSecurity, JPA, Groovy and many other technologies
-----------------------------------------------------------------------

*This project has been implemented based in* --> [Spring MVC 4 Quickstart Maven Archetype](https://github.com/kolorobot/spring-mvc-quickstart-archetype)

[![Build Status](https://travis-ci.org/leandrocgsi/erudio-api-with-groovy.svg?branch=master)](https://travis-ci.org/leandrocgsi/erudio-api-with-groovy)
[![Build Status](https://circleci.com/gh/leandrocgsi/erudio-api-with-groovy.svg?&style=shield)](https://circleci.com/gh/leandrocgsi/erudio-api-with-groovy/)

Summary
-------
The project is a Maven archetype for Spring MVC 4 web application.

Generated project characteristics
-------------------------
* No-xml Spring MVC 4 web application (except named queries)
* JPA (Hibernate/HSQLDB/Spring Data JPA)
* MongoDB (Spring Data Mongo)
* JUnit/Mockito
* Spring Security
* REST
* Swagger
* Groovy

How to get this project
----------------

You can execute following command or as you prefer [download zip here](https://github.com/leandrocgsi/erudio-api-with-groovy/archive/master.zip) and import in your favourite IDE.

```sh
git clone https://github.com/leandrocgsi/erudio-api-with-groovy.git
```


Run the project
----------------

```bash
	mvn test tomcat7:run
```

Test on the browser
-------------------

	http://localhost:8080/api/v1/{{endPointName}}

Note: No additional services are required in order to start the application. Mongo DB configuration is in place but it is not used in the code.

Switching to PostgreSQL
-----------------------

* Add dependency to PostgreSQL driver in POM:

```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>9.4.1207</version>
</dependency>
```

* Change `persistence.properties`:

```
dataSource.driverClassName=org.postgresql.Driver
dataSource.url=jdbc:postgresql:postgres
dataSource.username=postgres
dataSource.password=postgres

hibernate.dialect=org.hibernate.dialect.PostgreSQL9Dialect
hibernate.hbm2ddl.auto=create
```

Se especialize
------------------------


[<img src="https://github.com/leandrocgsi/SpringBootPlayground/blob/master/Images/banner_blog_udemy_course_sring_boot.jpg?raw=true">](https://www.udemy.com/restful-apis-do-0-a-nuvem-com-springboot-e-docker/?couponCode=GTHB_REPOS_SALE2019)
