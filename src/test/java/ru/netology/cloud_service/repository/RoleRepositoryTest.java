//package ru.netology.cloud_service.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.netology.cloud_service.TestData.ROLE_USER;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class RoleRepositoryTest {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @BeforeEach
//    void setUp() {
//        roleRepository.save(ROLE_USER);
//    }
//
//    @Test
//    void findByName() {
//        assertEquals(ROLE_USER,roleRepository.findByName("ROLE_USER").get());
//    }
//}