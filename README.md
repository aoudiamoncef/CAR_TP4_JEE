## TP4 - Book library

This project is based on lightweight and easy-to-use skeleton to create a JEE application that uses [Apache TomEE](http://openejb.apache.org/apache-tomee.html), a complete JEE server based on Tomcat.

### Structure

```
|____resources
| |____META-INF
| | |____persistence.xml
|____java
| |____car
| | |____tp4
| | | |____entity
| | | | |____JdbcBean.java
| | | | |____InitBean.java
| | | | |____BookBean.java
| | | | |____Book.java
| | | | |____CartBean.java
| | | |____servlet
| | | | |____AddBookServlet.java
| | | | |____DisplayAuthorsServlet.java
| | | | |____LogoutServlet.java
| | | | |____BookServlet.java
| | | | |____LoginServlet.java
| | | | |____CartServlet.java
| | | | |____SortByYearDescServlet.java
| | | | |____DeleteAddBookServlet.java
| | | | |____SortByYearAscServlet.java
| | | | |____FindAuthorServlet.java
| | | | |____FinderServlet.java
| | | | |____StockServlet.java
|____webapp
| |____index.jsp
| |____css
| | |____style.css
| |____WEB-INF
| | |____jsp
| | | |____books.jsp
| | | |____sortByYearDesc.jsp
| | | |____stock.jsp
| | | |____displayAuthors.jsp
| | | |____cart.jsp
| | | |____addbook.jsp
| | | |____order.jsp
| | | |____findAuthor.jsp
| | | |____sortByYearAsc.jsp
| | | |____finder.jsp
| | |____lib
| | | |____javaee-api-6.0-6-sources.jar
| | | |____javaee-api-6.0-6-javadoc.jar
| | | |____jstl-1.2.jar
| | | |____javaee-api-6.0-6.jar
| | |____web-app.xml
| |____js
| | |____login.js
|____main.iml

```

  * `main/java/car/tp4/entity`
    
    Contains all entities (EJB) (`Book` entity, `BookBean` bean examples).
    
  * `main/java/car/tp4/servlet`
  
    Contains all servlets (`BookServlet` example).
    
  * `resources/META-INF`
    
    Contains all the configuration files for the deployment.
    `persistence.xml` declares how to persist the app beans.
    We have to write a `persistence-unit` for each entity of the application.
    
  * `webapp/jsp`
  
  Contains all the jsp files, excepts the index.
  
  * `webapp/WEB-INF`
  
  Contains all the configuration files for the web application.

  For users database, you have just to run the script * `/login_bdd.sql
  
  ```
  /*create user db */
 CREATE USER 'jee'@'localhost' IDENTIFIED BY 'root';
 
 /* create your data base */
 CREATE DATABASE bdd_jee DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
 
 /* create users table */
 CREATE TABLE  bdd_jee.User (  id INT( 11 ) NOT NULL AUTO_INCREMENT , 
  email VARCHAR( 60 ) NOT NULL ,  password VARCHAR( 32 ) NOT NULL ,  
  name VARCHAR( 20 ) NOT NULL ,  inscription_date DATETIME NOT NULL , 
   PRIMARY KEY ( id ),  UNIQUE ( email ) ) ENGINE = MariaDB;
 
 /* insert users */
 INSERT INTO bdd_jee.User (email, password, name, inscription_date) 
 VALUES ('username@domain.com', MD5('username'), 'password', NOW());
 ```


### How to?

To build the application and to start the server:
```
mvn clean package tomee:run
```

Once started, the application is now reachable at:
```
http://localhost:8080
```

A Servlet and a JSP file is available for testing at:
```
http://localhost:8080/books
```


When developing, all the static resources (html, css, jsp) are automatically re-deployed on the server (in few seconds).

For the Java class, you can open a new terminal (without to stop the server), and package the application (`mvn package`) for a new automatic redeployment.

To clean all data and remove the application, use `mvn:clean`.
## Realisation 

### Code Requirement
* CMP The code compiled correctly (Maven or other compilation system) 
* DOC The code is properly documented (Readme.md, Javadoc) 
* TST The code is properly tested (unit tests under JUnit) (TO DO) 
* COO The code is designed following the principles of object design 
* AJL It is possible to record without online book (title, author, year) 
* AFL It is possible to display the available books and their details 
* AJP It is possible to add several books in an electronic shopping cart 
* CMP It is possible to validate the electronic shopping cart in the form of an order 
* GSL It is possible to manage the stock of books (availability and update according to orders) 
* RLT It is possible to search without a book with a part of its title 
* ALA It is possible to display all the books of an author (available or not) 
* TLP It is possible to sort books by year of publication 

## Code sample 
```
  /**
   * find book from given author
   * @param author
   * @return list of fended books
   */
  public List<Book> findAuthor(String author){
    Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.year DESC ");
    List<Book> findedBookList = query.getResultList();
    ArrayList<Book> findedBookAuthor = new ArrayList <Book>();
    String authorSearch = Normalizer.normalize(author.toLowerCase(),Normalizer.Form.NFD).replaceAll("\\s","").replaceAll("[^\\p{ASCII}]","");

    for (Book book : findedBookList) {
      if (Normalizer.normalize(book.getAuthor().toLowerCase(), Normalizer.Form.NFD).replaceAll("\\s", "").replaceAll("[^\\p{ASCII}]", "").contains(authorSearch)) {
        findedBookAuthor.add(book);
      }
    }
    return findedBookAuthor;
  }
```

## To do
* TST The code is properly tested (unit tests under JUnit)
