ENote
=====
ENote is short-term practice (students project) with the primary objective to implement most 
important functionality of electronic notes by the Spring framework.
```diff
Special features:
+ transaction isolation set;
+ Spring cache set;
+ generic dao (both jdbcTemplate and Jpa profiles have been implemented), generic service layer;
+ h2 sql initialization for tests with 500 units of all entities (except auxiliary many-many 
tble);
+ rest controllers;
+ performance benchmark by Spring Aspect, swagger;
+ globalExceptionHandler for add layers, checked/unchecked exceptions.
```

#### DB scheme:
![DB scheme](http://s1.radikale.ru/uploads/2017/12/22/992fda9cf4f8cc01c49eec3b861f9add-full.png)

#### Table of requests:
|URL path|Request method|Result|
|--------|--------------|------|
|**USERS**|
|/|GET|Hello|
|/users|GET|Get all users|
|/users|POST|Create user|
|/users/{id}|GET|Get user by id|
|/users/{id}|DELETE|Delete user by id|
|**NOTES**|
|/notes|GET|Get all notes|
|/notes|POST|Create note|
|/notes/tags/{id}|GET|Get notes by tagId|
|/notes/{id}|GET|Get note by id|
|/notes/{id}|DELETE|Delete note by id|
|/notes/{id}/tags|GET|Get tags by noteId|
|/notes/{noteId}/tags/{tagId}|POST|Add tag to note with noteId and tagId|
|**NOTEPADS**|
|/notepads|GET|Get all notepads|
|/notepads|POST|Create notepad|
|/notepads/{id}|GET|Get notepad by id|
|/notepads/{id}|DELETE|Delete notepad by id|
|**TAGS**|
|/tags|GET|Get all tags|
|/tags|POST|Create tag|
|/tags/{id}|GET|Get tag by id|
|/tags/{id}|DELETE|Delete tag by id|
|/users/{id}/tags|GET|Get tag by userId|

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
+ 3.1.3 NoteServiceTest class, NoteTestData implementation (completed), initDB.sql, populateDB.sql (competed);
+ 3.1.4 TagServiceTest class, TagTestData implementation (completed), initDB.sql, populateDB.sql (competed);
+ 3.2 ServiceValidatorUtilTest (completed);
+ 3.3 Mock test Services(completed);
```
##### 4 Step implement Spring+JPA package (in progress):
```diff
+ 🏹 4.1 Implement Spring data JPA profile/package (completed 100%):
+ 4.1.1 Switched back to JPA - debugging of JPA profile (completed 100%):
+ 4.1.1 Abstract Entity's @id Generation has been fixed (completed);
+ 4.1.2 AbstractNamedEntity upgraded (completed);
+ 4.1.3 constructors of the entities upgraded, fetch of the fields changed (completed);
+ 4.1.4 User's duplicate (email column) has been deleted (completed);
+ 4.1.5 JpaAbstractGenericDao has been fixed (completed);
+ 4.1.6 Spring-app.xml has been fixed (completed);
+ 4.2 Upgrade tests (completed);
+ 4.2.1 TagServiceTest, NotepadTestData, NoteTestData, TagTestData have been fixed (completed); 
+ 4.3 Merge conflicts with Jdbc profile (completed);
+ 4.4 Finalize the debugging of JPA (completed 100 %);
```
##### 5 Step implement controllers, performance benchmark,exceptions handling system(completed):
```diff
+ 5.1 Implement Controllers layer, add functionality and tests (completed 100%):
+ 5.1.1 switched to JavaConfig, added basic restControllers(completed);
+ 5.1.2 manyToMany relationship has been implemented; initDB.sql/populateDB.sql fixed (100%);
+ 5.1.3 AbstractNamedEntity upgraded (completed);
+ 5.1.4 Services have been upgraded, functionality added, refactoring (completed);
(completed);
+ 5.1.5 The functionality have been added to controllers, tests of controllers (completed);
+ 5.2 Create benchmark class via Spting aspect's (completed)(completed);
+ 🏹 5.3 GlobalExceptionsIntercepter (checked/unchecked exceptions), finalization (completed 100%):
+ 5.3.1 Auxiliary exceptions classes and superclass ApplicationException (completed);
+ 5.3.2 GlobalControllerEcxeptionHandler and GlobalControllerValidatorUtil (completed);
+ 5.3.3 Connect swagger, DB scheme, link's table, fix Readme, release 3.0 (finalized 100%);
```
##### 6 Step code performance refactoring, set transaction isolation and cache (optional):
```diff
+ 6.1 Performance refactoring:
+ 6.1.1 Service and Dao layers refactoring (completed 100%):
+ 6.1.1.1 Replace @Transactional annotations to the Service layer from Dao (completed); 
+ 6.1.1.2 Upgrade repository.jpa package structure; upgrade method's logic (completed); 
+ 6.1.2 Refactor data source, optimise transaction isolation (completed 100%):
+ 6.1.2.1 Upgrade data source configuration (completed);
+ 6.1.2.1 Change data source transaction isolation level to Serializable (completed);
+ 6.1.3 Enable Spring cache (completed 100%):
+ 6.1.3.1 Set appconfig, spring-cache config, ehcache config (completed);
+ 6.1.3.2 Install cache to Service Layer, check enable, delete deprecated service tests 
(competed);
 + 6.1.3 Enable Hibernate level 2 cache (completed 90%):
 in progress ...
```
#### Prerequisites:
- IntelliJ IDEA.

#### Built With:
- Apache Maven.

#### Authors
- Alexey Parshin
- Konstantin Mavropulo

#### Acknowledgments:
Grigory Kislin's popular repositories