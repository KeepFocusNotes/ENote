ENote
=====
ENote is student project with the primary objective to implement most important functionality of 
electronic notes by the Spring framework.
Initial DB scheme:

![Initial DB scheme](http://s019.radikal.ru/i617/1712/20/b6160f97211b.jpg)

### Plan
#####1 Step (using JDBC and Spting):
- init repository (completed);
- init/set new Maven project and directory's structure (completed);
- set initial db scheme (completed);
- spring-app.xml (completed);
- spring-db.xml (completed);
- h2.properties (completed);
- entities (mostly completed).
#####2 Step implement Spring+JDBC package(70%):
- JDBCAbstractGenericDao (completed);
- JDBCUserRepositoryImpl (completed);
- JDBCNotepadRepositoryImpl (completed);
- initDB.sql (completed for users and notepads (16403 entities in each));
- populateDB.sql (completed for users and notepads (16403 entities in each));
- ServiceValidatorUtil (completed);
- NotFoundException (completed);
- generic services structure implemented (progress 90%);
- test Services;
- test DAO;
...
...
#####3 Step implement Spring+JPA package;
- implement Spring data JPA package;
 
#### Prerequisites:
- IntelliJ IDEA.

#### Built With:
- Apache Maven.

#### Authors
- Alexey Parshin
- Konstantin Mavropulo

#### Acknowledgments:
//TODO