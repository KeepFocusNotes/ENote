ENote
=====
ENote is student project with the primary objective to implement most important functionality of 
electronic notes by the Spring framework.
Initial DB scheme:

![Initial DB scheme](http://s019.radikal.ru/i617/1712/20/b6160f97211b.jpg)

### Plan
####1 Step (using JDBC and Spring):
- init repository (completed);
- init/set new Maven project and directory's structure (completed);
- set initial db scheme (completed);
- spring-app.xml (completed);
- spring-db.xml (completed);
- h2.properties (completed);
- entities (mostly completed).
####2 Step implement Spring+JDBC package(70%):
- Jdbc package installation:
   - JdbcAbstractGenericDao (completed);
   - JdbcUserRepositoryImpl (completed);
   - JdbcNotepadRepositoryImpl (completed);
   - JdbcNoteRepositoryImpl (completed);
   - JdbcTagRepositoryImpl (completed);
- initDB.sql (completed for users and notepads (16403 entities in each));
- populateDB.sql (completed for users and notepads (16403 entities in each));
- ServiceValidatorUtil (completed);
- NotFoundException (completed);
- Generic services structure implemented (completed 90%):
   - GenericService and JdbcGenericServiceImpl (competed);
   - UserService, NotepadService, NoteService, TagService added (completed);
   - jdbc UserServiceImpl, NotepadServiceImpl, NoteServiceImpl, TagServiceImpl (completed);
####3 Step test Services and Dao: 
- test Services(in progress);
- test DAO;

####4 Step implement Spring+JPA package:
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