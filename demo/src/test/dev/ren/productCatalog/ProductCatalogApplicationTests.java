package dev.ren.productCatalog;


import dev.ren.productCatalog.inheritanceExample.joinedtable.repository.JTMentorRepository;
import dev.ren.productCatalog.inheritanceExample.joinedtable.repository.JTUserRepository;
import dev.ren.productCatalog.inheritanceExample.mappedsuperclass.repository.MSCInstructorRepository;
import dev.ren.productCatalog.inheritanceExample.mappedsuperclass.repository.MSCMentorRepository;
import dev.ren.productCatalog.inheritanceExample.mappedsuperclass.repository.MSCTARepository;
import dev.ren.productCatalog.inheritanceExample.singletable.repository.STInstructorRepository;
import dev.ren.productCatalog.inheritanceExample.singletable.repository.STMentorRepository;
import dev.ren.productCatalog.inheritanceExample.singletable.repository.STTARepository;
import dev.ren.productCatalog.inheritanceExample.singletable.repository.STUserRepository;
import dev.ren.productCatalog.inheritanceExample.tableperclass.repository.TPCInstructorRepository;
import dev.ren.productCatalog.inheritanceExample.tableperclass.repository.TPCMentorRepository;
import dev.ren.productCatalog.inheritanceExample.tableperclass.repository.TPCTARepository;
import dev.ren.productCatalog.inheritanceExample.tableperclass.repository.TPCUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductCatalogApplicationTests {

    @Autowired
    private JTUserRepository jtUserRepository;
    @Autowired
    private JTMentorRepository jtMentorRepository;
    @Autowired
    private TPCUserRepository tpcUserRepository;
    @Autowired
    private TPCMentorRepository tpcMentorRepository;
    @Autowired
    private TPCInstructorRepository tpcInstructorRepository;
    @Autowired
    private TPCTARepository tpcTARepository;
    @Autowired
    private STUserRepository stUserRepository;
    @Autowired
    private STMentorRepository stMentorRepository;
    @Autowired
    private STInstructorRepository stInstructorRepository;
    @Autowired
    private STTARepository stTARepository;
    @Autowired
    private MSCTARepository mscTARepository;
    @Autowired
    private MSCMentorRepository mscMentorRepository;
    @Autowired
    private MSCInstructorRepository mscInstructorRepository;

	@Test
	void contextLoads() {
	}

    @Test
    public void testInheritanceType_joined(){
        dev.ren.productCatalog.inheritanceExample.joinedtable.User userJt = new dev.ren.productCatalog.inheritanceExample.joinedtable.User();
        userJt.setEmail("naman@scaler.com");
        userJt.setPassword("Password");
        jtUserRepository.save(userJt);

        dev.ren.productCatalog.inheritanceExample.joinedtable.Mentor mentorJt = new dev.ren.productCatalog.inheritanceExample.joinedtable.Mentor();
        mentorJt.setEmail("NamanBhalla@scaler.com");
        mentorJt.setPassword("password");
        mentorJt.setNoOfSessions(50);
        mentorJt.setNoOfMentees(4);
        jtMentorRepository.save(mentorJt);
    }

    @Test
    public void testInheritanceType_table_per_class(){
        dev.ren.productCatalog.inheritanceExample.tableperclass.User userTpc = new dev.ren.productCatalog.inheritanceExample.tableperclass.User();
        userTpc.setEmail("naman@scaler.com");
        userTpc.setPassword("Password");
        tpcUserRepository.save(userTpc);

        dev.ren.productCatalog.inheritanceExample.tableperclass.Mentor mentorTpc = new dev.ren.productCatalog.inheritanceExample.tableperclass.Mentor();
         mentorTpc.setEmail("NamanBhalla@scaler.com");
        mentorTpc.setPassword("password");
        mentorTpc.setNoOfSessions(50);
        mentorTpc.setNoOfMentees(4);
        tpcMentorRepository.save(mentorTpc);

        dev.ren.productCatalog.inheritanceExample.tableperclass.Instructor instructorTpc = new dev.ren.productCatalog.inheritanceExample.tableperclass.Instructor();
        instructorTpc.setEmail("Naman@scaler.com");
        instructorTpc.setPassword("password");
        instructorTpc.setSalary(500000.00);
        instructorTpc.setHandsome(true);
        tpcInstructorRepository.save(instructorTpc);

        /// use constructor to create objects, not through setter getter, very confusing
        dev.ren.productCatalog.inheritanceExample.tableperclass.TA taTpc = new dev.ren.productCatalog.inheritanceExample.tableperclass.TA("anjali@scaler.com",4);
        tpcTARepository.save(taTpc);

    }

    @Test
    public void testInheritanceType_singleTable(){
        dev.ren.productCatalog.inheritanceExample.singletable.User userSt = new dev.ren.productCatalog.inheritanceExample.singletable.User();
        userSt.setEmail("naman_st@scaler.com");
        userSt.setPassword("Password_st");
        stUserRepository.save(userSt);

        dev.ren.productCatalog.inheritanceExample.singletable.Mentor mentorSt = new dev.ren.productCatalog.inheritanceExample.singletable.Mentor();
        mentorSt.setEmail("NamanBhalla_st@scaler.com");
        mentorSt.setPassword("password_st");
        mentorSt.setNoOfSessions(75);
        mentorSt.setNoOfMentees(45);
        stMentorRepository.save(mentorSt);

        dev.ren.productCatalog.inheritanceExample.singletable.Instructor instructorSt = new dev.ren.productCatalog.inheritanceExample.singletable.Instructor();
        instructorSt.setEmail("Naman_st@scaler.com");
        instructorSt.setPassword("password_st");
        instructorSt.setHandsome(true);
        stInstructorRepository.save(instructorSt);

        /// use constructor to create objects, not through setter getter, very confusing
        dev.ren.productCatalog.inheritanceExample.singletable.TA taSt = new dev.ren.productCatalog.inheritanceExample.singletable.TA("anjali_st@scaler.com",5);
        stTARepository.save(taSt);
    }

    @Test
    public void testInheritanceType_mappedSuperClass(){
        //User is abstract class and no associated table in DB. Cannot instantiate the User class
      //  dev.ren.productCatalog.inheritanceExample.mappedsuperclass.User userMsc = new dev.ren.productCatalog.inheritanceExample.mappedsuperclass.User();
//        userMsc.setEmail("naman_st@scaler.com");
//        userMsc.setPassword("Password_st");
//        mscUserRepository.save(userMsc);

        String email = "NamanBhalla_msc@scaler.com";
        String password= "password_msc";
        int noOfSessions= 75;
        int noOfMentees= 45;
        dev.ren.productCatalog.inheritanceExample.mappedsuperclass.Mentor mentorMsc = new dev.ren.productCatalog.inheritanceExample.mappedsuperclass.Mentor(email, password,noOfSessions,noOfMentees);

        mscMentorRepository.save(mentorMsc);

        dev.ren.productCatalog.inheritanceExample.mappedsuperclass.Instructor instructorMsc = new dev.ren.productCatalog.inheritanceExample.mappedsuperclass.Instructor(email,true);
        mscInstructorRepository.save(instructorMsc);

        /// use constructor to create objects, not through setter getter, very confusing
        dev.ren.productCatalog.inheritanceExample.mappedsuperclass.TA taMsc = new dev.ren.productCatalog.inheritanceExample.mappedsuperclass.TA("anjali_msc@scaler.com","secret_23",4);
        mscTARepository.save(taMsc);
    }

}
