ENote
=====
ENote is student project with the primary objective to implement most important functionality of 
electronic notes by the Spring framework.
Initial DB scheme:

![Initial DB scheme](http://s019.radikal.ru/i617/1712/20/b6160f97211b.jpg)

#### Plan:
1 Step (using JDBC and Spting):
- init repository (completed);
- init/set new Maven project and directory's structure (completed);
- set initial db scheme (completed);
- spring-app.xml (completed);
- spring-db.xml (completed);
- h2.properties (completed);
- entities (mostly completed);

//TODO:
- create common interfaces;
- implements Spring+JDBC package(70%)
 - JDBCAbstractGenericDao (completed);
 - JDBCUserRepositoryImpl (completed);
 - JDBCNotepadRepositoryImpl (completed);
 - initDB.sql (completed for users and notepads (16403 entities in each));
 - populateDB.sql (completed for users and notepads (16403 entities in each))
 - tests
 - other entities' repos...
...
}

 
#### Prerequisites:
- IntelliJ IDEA.

#### Built With:
- Apache Maven.

#### Authors
- Alexey Parshin
- Konstantin Mavropulo

#### Acknowledgments:
//TODO