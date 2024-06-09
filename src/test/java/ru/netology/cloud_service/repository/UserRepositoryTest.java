//package ru.netology.cloud_service.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.netology.cloud_service.TestData.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class UserRepositoryTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//        roleRepository.save(ROLE_USER);
//        userRepository.save(USER);
//    }
//
//    @Test
//    void findByUserName() {
//        assertEquals(USER, userRepository.findByUserName(USERNAME).get());
//    }
//}