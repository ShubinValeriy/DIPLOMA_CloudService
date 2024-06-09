//package ru.netology.cloud_service.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import ru.netology.cloud_service.model.entities.StorageFile;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.netology.cloud_service.TestData.*;
//import static ru.netology.cloud_service.TestData.STORAGE_FILE_2;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class StorageFileRepositoryTest {
//
//
//    @Autowired
//    private StorageFileRepository storageFileRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @BeforeEach
//    void setUp() {
//        userRepository.deleteAll();
//        roleRepository.deleteAll();
//        storageFileRepository.deleteAll();
//        roleRepository.save(ROLE_USER);
//        userRepository.save(USER);
//        storageFileRepository.save(STORAGE_FILE_1);
//        storageFileRepository.save(STORAGE_FILE_2);
//    }
//
//    @Test
//    void deleteByUserAndFileName() {
//        StorageFile beforeDelete = storageFileRepository.findByUserAndFileName(
//                USER, STORAGE_FILE_1.getFileName()
//        );
//        assertEquals(beforeDelete, STORAGE_FILE_1);
//        storageFileRepository.deleteByUserAndFileName(USER, FILE_NAME_1);
//        StorageFile afterDelete = storageFileRepository.findByUserAndFileName(
//                USER, STORAGE_FILE_1.getFileName()
//        );
//        assertNull(afterDelete);
//    }
//
//    @Test
//    void findByUserAndFileName() {
//        assertEquals(STORAGE_FILE_2, storageFileRepository.findByUserAndFileName(USER, FILE_NAME_2));
//    }
//
//    @Test
//    void findAllByUser() {
//        assertEquals(List.of(STORAGE_FILE_1,STORAGE_FILE_2), storageFileRepository.findAllByUser(USER));
//    }
//
//    @Test
//    void editFileNameByUser() {
//        storageFileRepository.editFileNameByUser(USER, FILE_NAME_1, NEW_FILE_NAME_1);
//        StorageFile beforeEditName = storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1);
//        StorageFile afterEditName = storageFileRepository.findByUserAndFileName(USER, NEW_FILE_NAME_1);
//        assertNull(beforeEditName);
//        assertNotNull(afterEditName);
//    }
//}