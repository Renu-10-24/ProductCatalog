## types of inheritance

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

###  HOW and Why JOIN FETCH works

Gemini said
In Hibernate, the transition between entity states and the internal workings of proxies are the "engine" behind the LazyInitializationException you are exploring in your Scaler class.

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

https://www.baeldung.com/hibernate-inheritance

-------------------------------------------------------------------------------------------------------
## SpringDataJpa - already converts the checked exceptions like SQLException  into DataAccessException - a RuntimeException 

-------------------------------------------------------------------------------------------------------
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

//Preservation of root cause by calling super(message,cause); in the constructor of new BusinessExcpetion that extends RuntimeException

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