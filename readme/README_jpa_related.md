## types of inheritance
https://www.baeldung.com/hibernate-inheritance 

## Table Per Class - InheritanceType.TABLE_PER_CLASS
@Entity on all classes, parent and children, all classes have tables in DB
Each class' table will have attributes of its own and also the attributes inherited from parent

## SingleTable -- InheritanceType.SINGLE_TABLE, needs a Discriminator column
Creates a single table with all the attributes of all children and parent

## JoinedTable -- InheritanceType.JOINED needs a @PrimaryKeyJoinColumn(name="user_id") on the Primary key
1 table per class.
Each class' table ONLY has ITS OWN attributes. Attributes of parent are accessed via a foreign key to the parent class

## MappedSuperClass -- NO parent entity table, only child tables, so NO @entity on parent class, only @MappedSuperClass
Like Table per class. Only difference - No table for parent class

Difference in EACH inheritance type can be understood with the ddl statements hibernate runs in the background 
## Just used create to drop and create all tables once. -- springboot is intelligently dropping only the tables that it is creating nw, not touching the tables already existing in the schema

Hibernate: alter table jt_instructor drop foreign key FK4od6mbg1v99qri5dtqreaayou
Hibernate: alter table jt_mentor drop foreign key FK74kd6ct4a7jq51dr84f8m7usr
Hibernate: alter table jt_ta drop foreign key FKhq7nv0qp5o8md1xwoglkc7g7k

Hibernate: alter table product drop foreign key FK1mtsbur82frn64de7balymq9s

Hibernate: drop table if exists category

Hibernate: drop table if exists jt_instructor
Hibernate: drop table if exists jt_mentor
Hibernate: drop table if exists jt_ta

Hibernate: drop table if exists jt_user
Hibernate: drop table if exists jt_user_seq


Hibernate: drop table if exists mpsc_instructor
Hibernate: drop table if exists mpsc_instructor_seq
Hibernate: drop table if exists mpsc_mentor
Hibernate: drop table if exists mpsc_mentor_seq
Hibernate: drop table if exists mpsc_ta
Hibernate: drop table if exists mpsc_ta_seq

Hibernate: drop table if exists product

Hibernate: drop table if exists sc_user
Hibernate: drop table if exists sc_user_seq

Hibernate: drop table if exists tpc_instructor
Hibernate: drop table if exists tpc_mentor
Hibernate: drop table if exists tpc_ta

Hibernate: drop table if exists tpc_user
Hibernate: drop table if exists tpc_user_seq

Hibernate: create table category (id binary(16) not null, name varchar(255), primary key (id)) engine=InnoDB

## Joined table

## Hibernate: create table jt_instructor (is_handsome bit not null, user_id bigint not null, primary key (user_id)) engine=InnoDB

## Hibernate: create table jt_mentor (no_of_mentees integer not null, no_of_sessions integer not null, user_id bigint not null, primary key (user_id)) engine=InnoDB

## Hibernate: create table jt_ta (average_rating integer not null, user_id bigint not null, primary key (user_id)) engine=InnoDB

## Hibernate: create table jt_user (id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB

Hibernate: create table jt_user_seq (next_val bigint) engine=InnoDB
Hibernate: insert into jt_user_seq ( next_val ) values ( 1 )
Hibernate: alter table jt_instructor add constraint FK4od6mbg1v99qri5dtqreaayou foreign key (user_id) references jt_user (id)
Hibernate: alter table jt_mentor add constraint FK74kd6ct4a7jq51dr84f8m7usr foreign key (user_id) references jt_user (id)
Hibernate: alter table jt_ta add constraint FKhq7nv0qp5o8md1xwoglkc7g7k foreign key (user_id) references jt_user (id)

## ################# just so its highlighted in the MD file

##  Hibernate: create table mpsc_instructor (is_handsome bit not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB

Hibernate: create table mpsc_instructor_seq (next_val bigint) engine=InnoDB
Hibernate: insert into mpsc_instructor_seq ( next_val ) values ( 1 )

## Hibernate: create table mpsc_mentor (no_of_mentees integer not null, no_of_sessions integer not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB

Hibernate: create table mpsc_mentor_seq (next_val bigint) engine=InnoDB
Hibernate: insert into mpsc_mentor_seq ( next_val ) values ( 1 )

## Hibernate: create table mpsc_ta (average_rating integer not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table mpsc_ta_seq (next_val bigint) engine=InnoDB
Hibernate: insert into mpsc_ta_seq ( next_val ) values ( 1 )

Hibernate: create table product (price float(53) not null, category_id binary(16), id binary(16) not null, description varchar(255), image varchar(255), title varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table sc_user (average_rating integer, is_handsome bit, no_of_mentees integer, no_of_sessions integer, user_type integer not null check ((user_type in (1,2,3,4))), id bigint not null, email varchar(255), password varchar(255), primary key (id), check (user_type <> 2 or (is_handsome is not null)), check (user_type <> 3 or (no_of_mentees is not null and no_of_sessions is not null)), check (user_type <> 4 or (average_rating is not null))) engine=InnoDB
Hibernate: create table sc_user_seq (next_val bigint) engine=InnoDB
Hibernate: insert into sc_user_seq ( next_val ) values ( 1 )

## Hibernate: create table tpc_instructor (is_handsome bit not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB
## Hibernate: create table tpc_mentor (no_of_mentees integer not null, no_of_sessions integer not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB
## Hibernate: create table tpc_ta (average_rating integer not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB
## Hibernate: create table tpc_user (id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB

Hibernate: create table tpc_user_seq (next_val bigint) engine=InnoDB
Hibernate: insert into tpc_user_seq ( next_val ) values ( 1 )


Hibernate: alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category (id)

## Mapped Super Class



Hibernate: drop table if exists category
Hibernate: drop table if exists jt_instructor
Hibernate: drop table if exists jt_mentor
Hibernate: drop table if exists jt_ta
Hibernate: drop table if exists jt_user
Hibernate: drop table if exists jt_user_seq
Hibernate: drop table if exists mpsc_instructor
Hibernate: drop table if exists mpsc_instructor_seq
Hibernate: drop table if exists mpsc_mentor
Hibernate: drop table if exists mpsc_mentor_seq
Hibernate: drop table if exists mpsc_ta
Hibernate: drop table if exists mpsc_ta_seq
Hibernate: drop table if exists product
Hibernate: drop table if exists sc_user
Hibernate: drop table if exists sc_user_seq
Hibernate: drop table if exists tpc_instructor
Hibernate: drop table if exists tpc_mentor
Hibernate: drop table if exists tpc_ta
Hibernate: drop table if exists tpc_user
Hibernate: drop table if exists tpc_user_seq
Hibernate: create table category (id binary(16) not null, name varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table jt_instructor (is_handsome bit not null, user_id bigint not null, primary key (user_id)) engine=InnoDB
Hibernate: create table jt_mentor (no_of_mentees integer not null, no_of_sessions integer not null, user_id bigint not null, primary key (user_id)) engine=InnoDB
Hibernate: create table jt_ta (average_rating integer not null, user_id bigint not null, primary key (user_id)) engine=InnoDB
Hibernate: create table jt_user (id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table jt_user_seq (next_val bigint) engine=InnoDB
Hibernate: insert into jt_user_seq ( next_val ) values ( 1 )
Hibernate: create table mpsc_instructor (is_handsome bit not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table mpsc_instructor_seq (next_val bigint) engine=InnoDB
Hibernate: insert into mpsc_instructor_seq ( next_val ) values ( 1 )
Hibernate: create table mpsc_mentor (no_of_mentees integer not null, no_of_sessions integer not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table mpsc_mentor_seq (next_val bigint) engine=InnoDB
Hibernate: insert into mpsc_mentor_seq ( next_val ) values ( 1 )
Hibernate: create table mpsc_ta (average_rating integer not null, id bigint not null, email varchar(255), password varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table mpsc_ta_seq (next_val bigint) engine=InnoDB
Hibernate: insert into mpsc_ta_seq ( next_val ) values ( 1 )
Hibernate: create table product (price float(53) not null, category_id binary(16), id binary(16) not null, description varchar(255), image varchar(255), title varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table sc_user (average_rating integer, is_handsome bit, no_of_mentees integer, no_of_sessions integer, user_type integer not null check ((user_type in (1,2,3,4))), id bigint not null, email varchar(255), password varchar(255), primary key (id), check (user_type <> 2 or (is_handsome is not null)), check (user_type <> 3 or (no_of_mentees is not null and no_of_sessions is not null)), check (user_type <> 4 or (average_rating is not null))) engine=InnoDB
Hibernate: create table sc_user_seq (next_val bigint) engine=InnoDB
Hibernate: insert into sc_user_seq ( next_val ) values ( 1 )
Hibernate: create table tpc_instructor (is_handsome bit not null, id bigint not null, email varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table tpc_mentor (no_of_mentees integer not null, no_of_sessions integer not null, id bigint not null, email varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table tpc_ta (average_rating integer not null, id bigint not null, email varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table tpc_user (id bigint not null, email varchar(255), primary key (id)) engine=InnoDB
Hibernate: create table tpc_user_seq (next_val bigint) engine=InnoDB
Hibernate: insert into tpc_user_seq ( next_val ) values ( 1 )
Hibernate: alter table jt_instructor add constraint FK4od6mbg1v99qri5dtqreaayou foreign key (user_id) references jt_user (id)
Hibernate: alter table jt_mentor add constraint FK74kd6ct4a7jq51dr84f8m7usr foreign key (user_id) references jt_user (id)
Hibernate: alter table jt_ta add constraint FKhq7nv0qp5o8md1xwoglkc7g7k foreign key (user_id) references jt_user (id)
Hibernate: alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category (id)

###  HOW and Why JOIN FETCH works - EAGER vs LAZY fetch
-- Couldn't find the select queries executed by Hibernate at all for hours, great learning curve
-- even though we were getting the ApplicationContext loaded with the contextLoads() test method, I changed the project to use a dependency for clearer logging of the query execution time and that caused the Application context not loading, beans nt initialized issues, to fix that, I had to use a resources file in the test folder with the same config as in the application resouces folder.

Reasons, - Hibernate has a first level cache PersistenceContext that cached the objects that were just saved / persisted to db. And so, it was using those objects to fetch the results instead of even hitting the database -- this would NOT even trigger the select queries (for Product obj, as well as Category obj).

One common culprit is the Jackson json serializer that would do a eager fetch to populate the object, but in our case, the select query not running at all, was due to the PERSISTENCE CONTEXT caching.

Also the Debugger runs the toString method that enables the eager fetch  (i.e., the objects inside the category object to be fetched eagerly) - with the setup

We really need to STEP INTO the code line by line to see when the Category object was fetched. And that leads to using the DEBUGGER. And the IDE Build Deploy Execution -> Debugger -> Java usually has the Settings Enable alternative view for Collection classes and Enable toString object view enabled by default that does an eager fetch to populate the fields to show.

Once disabled, I could see the toString method being called to populate the values in the Debugger a little later - for us to understand how it works internally.

Also, hibernate is smart enough to discard the insert/ update in the @Test annotated methods, even when we use @Transactional annotation. So, the inserts might done be flushed to DB at all - how do we know, if they persisted at all? - Look for the insert / update queries in the logs.
// so we flush to db first, to confirm the writes to DB actually take place and then, we need to get the EntityManager object injected manually, to use the clear() method to stop the L1 PersistenceContext caching that stops the fetching altogether.
And then, voila, we can see the select queries in logs, and find out that the LAZY FETCH works !

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

on the Product class.

---------------------------------------------------------------------------------------------------------------------
HOW LazyFetch works - internal implementation details in Hibernate 

Gemini said
In Hibernate, the transition between entity states and the internal workings of proxies are the "engine" behind the LazyInitializationException.

1. Hibernate Internal Proxy Creation
   When Hibernate loads an entity with FetchType.LAZY, it doesn't return your actual class. Instead, it uses a library (usually ByteBuddy) to generate a Proxy Class at runtime.

Structure: If your class is Instructor, Hibernate creates a class named something like Instructor$HibernateProxy$randomString. This proxy extends Instructor.

The LazyInitializer: Inside this proxy, there is a hidden field called LazyInitializer. It contains:

The Entity ID (e.g., id=101).

A reference to the Session (the database connection).

A boolean flag initialized=false.

Triggering the Proxy: When you call a method like instructor.getSalary(), the proxy intercepts the call.

If initialized == false, it uses the Session to run a SQL SELECT and fill the "real" target object.

It then delegates the method call to that real object.

2. Entity States: The Lifecycle
   As you saw in your Hibernate Schema Creation and Inheritance spreadsheet, entities move through different states during a transaction.

State	Context	Behavior
Transient	Before save()	New object in Java memory. No ID and no record in the DB.
Persistent	Inside @Transactional	Linked to a Session. Any change you make in Java is automatically updated in the DB (Dirty Checking).
Detached	After @Transactional	The Session is closed. The object still has an ID and data, but Hibernate is no longer "watching" it.
Removed	After delete()	Scheduled for deletion at the end of the transaction.

3. State Transitions (Before, During, and After)
   A. Before the Transaction (Transient)
   You create the object: Instructor i = new Instructor();. It is just an object in RAM. Hibernate doesn't know it exists.

B. Inside the Transaction (Persistent / Proxy)
When you call repository.findById(101), the entity enters the Persistent state.

The Session: Hibernate keeps the DB connection open.

Proxy Status: If you access a lazy collection (like batches), the Proxy is linked to the active Session. When you call getBatches(), the Proxy uses that active Session to fetch data.

C. After the Transaction (Detached)
The @Transactional method finishes, the transaction commits, and the Session is closed.

The Entity: The Instructor object is now Detached. You can still read getName() because that data was already loaded.

The Proxy Failure: If you haven't accessed getBatches() yet, that field is still a Proxy.

The Crash: Because the Session is closed, the Proxy's LazyInitializer has no database connection to use. When you call getBatches(), it throws the LazyInitializationException because it can't "re-attach" itself to a new session automatically.

Summary: Why JOIN FETCH works
As mentioned in your Representing Inheritance class, using JOIN FETCH moves the entity from the Proxy state to the Initialized state while the transaction is still active. This ensures the data is present even after the entity becomes Detached.
-----------------------------------------------------------------------------------------------------------------------------
## CascadeTypes
When we have a foreign key relation/ constraint - Category_id is foreign key in Product table

when there is no cascade type defined on Product and Category, 

when we set the category of a product to something that doesn't exist in the Category table, 
it throws 

we see this : 

org.springframework.dao.InvalidDataAccessApiUsageException: org.hibernate.TransientPropertyValueException: Persistent instance of 'dev.ren.productCatalog.models.Product' references an unsaved transient instance of 'dev.ren.productCatalog.models.Category' (persist the transient instance before flushing) [dev.ren.productCatalog.models.Product.category -> dev.ren.productCatalog.models.Category]
Caused by: java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: Persistent instance of 'dev.ren.productCatalog.models.Product' references an unsaved transient instance of 'dev.ren.productCatalog.models.Category' (persist the transient instance before flushing) [dev.ren.productCatalog.models.Product.category -> dev.ren.productCatalog.models.Category]

## Gemini said
In MySQL 8.0.44, foreign keys are more than just "types"—they are defined by their Referential Actions (the "Cascades") and their Index Requirements.

Senior developers distinguish between the Referential Action (what happens during a delete/update) and the Physical Constraint (the underlying index requirements).

## Difference between using EntityManager.persist() and productRepository.save() 

1. Why EntityManager throws IllegalStateException
   In your first test, you are using entityManager.persist(product).

The Level: This is the "Low Level" JPA API.

The Logic: When you call .persist() on a Product that has a Category that Hibernate doesn't know about (Transient), Hibernate realizes it cannot fulfill the contract of persisting the object.

The Result: Since you are calling the raw provider (Hibernate), it throws a Direct Exception. In many configurations, Hibernate wraps the "Transient Object" error as an IllegalStateException (specifically, a java.lang.IllegalStateException containing the message 'save the transient instance before flushing').

2. Why ProductRepository throws InvalidDataAccessApiUsageException
   In your second test, you are using productRepository.save(product).

The Level: This is the "High Level" Spring Data JPA API.

The Logic: Spring Data Repositories are wrapped in a Proxy. One of the primary jobs of this proxy is Exception Translation.

The Result: Spring catches the underlying Hibernate IllegalStateException (or TransientPropertyValueException) and translates it into Spring’s own Data Access Exception hierarchy.

InvalidDataAccessApiUsageException is Spring's way of saying: "You are using the JPA API incorrectly (i.e., trying to save a child before its parent)."




-----------------------------------------------------------------------------------------------------------------------------
## SpringDataJpa - already converts the checked exceptions like SQLException  into DataAccessException - a RuntimeException 
Exception Handling in Springboot
-----------------------------------------------------------------------------------------------------------------------------
public class DatabaseTechnicalException extends RuntimeException {
private final String internalErrorCode;

    public DatabaseTechnicalException(String message, Throwable cause, String internalErrorCode) {
        super(message, cause); // Always pass the 'cause' to preserve the stack trace
        this.internalErrorCode = internalErrorCode;
    }

    public String getInternalErrorCode() {
        return internalErrorCode;
    }
}

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUser(User user) {
        try {
            jdbcTemplate.update("INSERT INTO users (id, email) VALUES (?, ?)", 
                                user.getId(), user.getEmail());
        } catch (DataAccessException e) { 
            // DataAccessException is Spring's wrapper for SQLException
            throw new DatabaseTechnicalException(
                "Failed to persist user to database", 
                e, 
                "ERR_DB_001"
            );
        }
    }
}

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DatabaseTechnicalException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseException(DatabaseTechnicalException ex) {
        // 1. Log the full stack trace for internal debugging (ELK/Splunk)
        logger.error("Database error occurred: code={}, message={}", 
                     ex.getInternalErrorCode(), ex.getMessage(), ex);

        // 2. Return a sanitized response to the client
        ErrorResponse apiError = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "A technical error occurred in our data layer. Please try again later.",
            ex.getInternalErrorCode()
        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

//Preservation of root cause by calling super(message,cause); in the constructor of new BusinessException that extends RuntimeException

1. The Custom "Wrapper" Exception
   Notice the use of super(message, cause). This is the mechanism that chains the exceptions together in the stack trace.

Java
public class DataAccessException extends RuntimeException {
private final String errorCode;

    public DataAccessException(String message, Throwable cause, String errorCode) {
        // This is the magic line: it preserves the 'cause' (SQLException)
        super(message, cause); 
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

2. The Repository Layer (The "Wrapper")
   We catch the low-level SQLException and wrap it. We don't just log it and return null; we throw our custom exception so the transaction can roll back.

Java
@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void processOrder(Long orderId) {
        try {
            jdbcTemplate.update("UPDATE orders SET status = 'PROCESSED' WHERE id = ?", orderId);
        } catch (org.springframework.dao.DataAccessException e) {
            // We wrap the Spring/SQL exception into our Business Exception
            throw new DataAccessException(
                "Failed to update order status in DB", 
                e, // <--- PRESERVATION OF CAUSE
                "ORD_DB_001"
            );
        }
    }
}

Gemini said
In a production environment, "Preservation of Cause" is the difference between a 5-minute fix and a 5-hour debugging session. If you don't pass the original exception, you lose the stack trace, the SQL error code (like 54000 for stack depth limit), and the specific reason for failure.

Here is the production-grade implementation showing exactly how the Cause travels from the Database driver to your logs.

1. The Custom "Wrapper" Exception
   Notice the use of super(message, cause). This is the mechanism that chains the exceptions together in the stack trace.

Java
public class DataAccessException extends RuntimeException {
private final String errorCode;

    public DataAccessException(String message, Throwable cause, String errorCode) {
        // This is the magic line: it preserves the 'cause' (SQLException)
        super(message, cause); 
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
2. The Repository Layer (The "Wrapper")
   We catch the low-level SQLException and wrap it. We don't just log it and return null; we throw our custom exception so the transaction can roll back.

Java
@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void processOrder(Long orderId) {
        try {
            jdbcTemplate.update("UPDATE orders SET status = 'PROCESSED' WHERE id = ?", orderId);
        } catch (org.springframework.dao.DataAccessException e) {
            // We wrap the Spring/SQL exception into our Business Exception
            throw new DataAccessException(
                "Failed to update order status in DB", 
                e, // <--- PRESERVATION OF CAUSE
                "ORD_DB_001"
            );
        }
    }
}
3. The Global Handler (The "Logger")
   In production, you want to show the user a clean message but log the full technical details for yourself.

Java
@RestControllerAdvice
public class GlobalExceptionHandler {
private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataException(DataAccessException ex) {
        
        // This log statement will print BOTH our message AND the underlying SQLException
        // because the SLF4J logger knows how to traverse the 'cause' chain.
        log.error("Database Error [{}]: {}", ex.getErrorCode(), ex.getMessage(), ex);

        Map<String, Object> body = new HashMap<>();
        body.put("error", "Database Operation Failed");
        body.put("code", ex.getErrorCode());
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

What the Logs Look Like (The Result)
If you did not preserve the cause, your logs would only show:
DataAccessException: Failed to update order status in DB (No clue why!)

Because you did preserve the cause, your logs will look like this:

Plaintext
ERROR [OrderService] Database Error [ORD_DB_001]: Failed to update order status in DB
com.myapp.exception.DataAccessException: Failed to update order status in DB
at com.myapp.repository.OrderRepository.processOrder(OrderRepository.java:18)
... 20 more
## Caused by: java.sql.SQLException: Lock wait timeout exceeded; try restarting transaction
at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:129)
at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
... 45 more
Why this matters for a Senior Dev:
Chained Exceptions: The Caused by: section only appears if you use super(message, cause).

Root Cause Analysis: Without the Caused by section, you wouldn't know if the DB was down, if there was a syntax error, or if (as shown above) a Lock Timeout occurred.

Clean APIs: Your Service layer stays clean, but your logs stay detailed.

---------------------------------------------------------------------------------------------------------------------
@RestControllerAdvice
public class GlobalExceptionHandler {
private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataException(DataAccessException ex) {
        
        // This log statement will print BOTH our message AND the underlying SQLException
        // because the SLF4J logger knows how to traverse the 'cause' chain.
        log.error("Database Error [{}]: {}", ex.getErrorCode(), ex.getMessage(), ex);

        Map<String, Object> body = new HashMap<>();
        body.put("error", "Database Operation Failed");
        body.put("code", ex.getErrorCode());
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
-------------------------------------------------------------------------------------------------------

(other examples include DuplicationMappingException - when 2 entities try to use same db table, DataIntegrityViolationException ,etc)


---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
To login to TestContainer in MySQL Workbench (NOT recommended but, for thorough debugging in dev, test)

## How to Connect MySQL Workbench (If you really want to)
   If you still want to "see" the data for learning purposes, you have to stop the test from finishing (which kills the container).

Add a Breakpoint: Put a breakpoint on the last line of your test.

Find the Port: Look at your console logs. Testcontainers will print something like:
Creating container for image: mysql:8.0.44
Container mysql:8.0.44 is started: [localhost:32768]

Connect Workbench: * Hostname: localhost

Port: 32768 (or whatever the log said)

User/Pass: test/test (default for Testcontainers)



-----------------------------------------------------------------------------------------------------------------------------
1. The "Under the Hood" Mechanics
   Scenario A: category.setProducts(newProducts)
   When Hibernate loads your Category, the products field is not a standard ArrayList. It is a PersistentBag (a wrapper that tracks changes).

The Action: You throw away Hibernate's PersistentBag and replace it with a plain java.util.ArrayList.

Hibernate's Reaction: Hibernate loses its "tracking link" to that collection. It can no longer determine which specific items were added or removed.

The "Nuclear" Strategy: Because it can't be sure what changed, Hibernate takes the safest (but most expensive) route: It deletes every single row associated with that Category ID and re-inserts everything from the new list.

##  Hibernate Preferred way ----- Scenario B: clear() and addAll()

The Action: You keep the PersistentBag instance and modify its internal contents.

Hibernate's Reaction: Since the "wrapper" is still the same object, Hibernate's dirty-tracking logic remains active.

## The "Smart" Strategy: Hibernate compares the old state of the bag with the new state. It identifies exactly which IDs are missing (sends DELETE) and which are new (sends INSERT). If a product was in the list before and is still there, it does nothing.

Imagine a Category (ID: 1) that has 2 products (IDs: 10, 11). You want to change it to have Product 11 and a New Product 12.

First way of category.setProducts(newProducts)  -- would delete all existing and insert all from new list

In the Second way, Hibernate would still be able to track the existing items, would delete only unwanted products and insert new ones 


----------------------------------------------------------------------------------------------------------------------------
https://www.baeldung.com/jpa-cascade-types
https://www.baeldung.com/hibernate-lazy-eager-loading
----------------------------------------------------------------------------------------------------------------------------
(exclude = {"category"})

Or use @JsonManagedReference on parent and 
@JsonManagedBackReference on the child entity

(exclude = {"category"})
----------------------------------------------------------------------------------------------------------------------------
In a normal Spring service method annotated with @Transactional, you do NOT explicitly call commit. The COMMIT happens automatically if the method completes successfully.
But, in a test method, the transaction even though all successfull and atomic, it still is rolled back to avoid polluting the DB with test data.

Also, realized that in newer version of springboot, even though @Transactional is NOT mentioned on @Test, it still is not detaching the entity after each statement, it still uses the same TransactionManager session, so we have to explicitly detach the entity and write the tests
Sample logs for saveProductsForCategory_fail_for_detached_entity()
2026-03-09T08:20:19.783-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [dev.ren.productCatalog.ProductIntegrationTest.saveProductsForCategory_fail_for_detached_entity]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2026-03-09T08:20:19.784-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Opened new EntityManager [SessionImpl(2047238003<open>)] for JPA transaction
2026-03-09T08:20:19.791-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@626b8822]
2026-03-09T08:20:19.800-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2047238003<open>)] for JPA transaction
2026-03-09T08:20:19.800-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build as described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html#0.3
WARNING: A Java agent has been loaded dynamically (C:\Users\renur\.m2\repository\net\bytebuddy\byte-buddy-agent\1.17.8\byte-buddy-agent-1.17.8.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
Hibernate:
select
c1_0.id,
c1_0.name
from
category c1_0
where
c1_0.name=?
2026-03-09T08:20:20.261-05:00 TRACE 35352 --- [productCatalog] [           main] org.hibernate.orm.jdbc.bind              : binding parameter (1:VARCHAR) <- [Laptops]
2026-03-09T08:20:20.334-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [SessionImpl(2047238003<open>)] for JPA transaction
2026-03-09T08:20:20.334-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Participating in existing transaction
2026-03-09T08:20:20.346-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Participating transaction failed - marking existing transaction as rollback-only
2026-03-09T08:20:20.346-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Setting JPA transaction on EntityManager [SessionImpl(2047238003<open>)] rollback-only
2026-03-09T08:20:20.367-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction rollback
2026-03-09T08:20:20.368-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Rolling back JPA transaction on EntityManager [SessionImpl(2047238003<open>)]
2026-03-09T08:20:20.374-05:00 DEBUG 35352 --- [productCatalog] [           main] o.s.orm.jpa.JpaTransactionManager        : Closing JPA EntityManager after transaction

--------------------------------------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------------------------------------

Search for the Flyway migration process in Gemini chat history, how we use the ddl-auto property to validate
V1__init_schema.sql script is used to create the database, once its success, flyway maintains the v1 success status with its flyway_schema_history table in MySQL.
if you need any more changes, you need to move those changes to a v2__ 

## 1. Create the next migration file
   Create a new file in src/main/resources/db/migration/ named V2__add_inventory_to_product.sql.

SQL
-- Adding a new column to the existing product table
ALTER TABLE product
ADD COLUMN available_stock INT DEFAULT 0;

-- You can even do data migration in the same script
UPDATE product SET available_stock = 10 WHERE price > 100.0;

## 2. Update your Java Code
   Now, update your Product entity to match the new schema:

Java
@Entity
public class Product extends BaseModel {
// ... existing fields

    @Column(name = "available_stock")
    private int availableStock; 
}

## 3. How Flyway handles this internally
   When you start your Spring Boot app:

Flyway checks the flyway_schema_history table in MySQL.

It sees that V1 is already "Success".

It sees V2 is new.

It executes the ALTER TABLE command.

It records V2 as "Success".

Hibernate Validation kicks in, sees the new column exists, and the app starts happily.

## Pro-Tip: Handling the "Checksum" Error
If you ever edit the code inside V1__init_schema.sql after it has already run, Flyway will throw an error: Migration checksum mismatch.

## Why? Flyway ensures that nobody "rewrites history."

## The Fix: If you made a mistake in V1, don't edit V1. Instead, create a V3 to fix the mistake.

## The "I'm in Dev" Fix: If you really want to reset, use spring.flyway.clean-on-validation-error=true (Careful! This wipes the DB) or just wipe your Docker volume again.