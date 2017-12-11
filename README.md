ENote
=====
ENote is student project with the primary objective to implement most important functionality of 
electronic notes by the Spring framework.

### Plan

##### 1 Step (completed 100%):
```diff
+ 1.1 init repository (completed);
+ 1.2 init/set new Maven project and directory's structure (completed);
+ 1.3 set initial db scheme (completed);
+ 1.4 spring-app.xml (completed);
+ 1.5 spring-db.xml (completed);
+ 1.6 h2.properties (completed);
+ 1.7 entities (mostly completed).
```
##### 2 Step implement Spring+JDBC package (completed 100%):
```diff
+ 2.1 Jdbc package installation:
+ 2.1.1 JdbcAbstractGenericDao (completed);
+ 2.1.2 JdbcUserRepositoryImpl (completed);
+ 2.1.3 JdbcNotepadRepositoryImpl (completed);
+ 2.1.4 JdbcNoteRepositoryImpl (completed);
+ 2.1.5 JdbcTagRepositoryImpl (completed);
+ 2.2 initDB.sql (completed for users and notepads (16403 entities in each));
+ 2.3 populateDB.sql (completed for users and notepads (16403 entities in each));
+ 2.4 ServiceValidatorUtil (completed);
+ 2.5 NotFoundException (completed);
+ 2.6 Generic services structure implemented (completed 100%):
+ 2.6.1 GenericService and JdbcGenericServiceImpl (competed);
+ 2.6.2 UserService, NotepadService, NoteService, TagService added (completed);
+ 2.6.3 jdbc UserServiceImpl, NotepadServiceImpl, NoteServiceImpl, TagServiceImpl (completed);
```
##### 3 Step test all methods of Services, Dao layers and utils (completed 100%): 
```diff
+ 3.1 UserServiceTest class, covering all of GenericService and GenericDao methods of the service and dao levels (completed 100%):
+ 3.1.1 UserServiceTest class, UserTestData implementation (completed), initDB.sql, populateDB.sql (completed);
+ 3.1.2 NotepadServiceTest class, NotepadTestData implementation (completed), initDB.sql, populateDB.sql (competed);
+ 3.2 ServiceValidatorUtilTest (completed);
+ 3.3 Mock test Services(completed);
```
##### 4 Step implement Spring+JPA package (in progress):
```diff
- implement Spring data JPA package;
```
#### Prerequisites:
- IntelliJ IDEA.

#### Built With:
- Apache Maven.

#### Authors
- Alexey Parshin
- Konstantin Mavropulo

#### Acknowledgments:
//TODO

Initial DB scheme:

![Initial DB scheme](http://s019.radikal.ru/i617/1712/20/b6160f97211b.jpg)