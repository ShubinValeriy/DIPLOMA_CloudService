//package ru.netology.cloud_service;
//
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//import ru.netology.cloud_service.model.dtos.request.JwtRequest;
//import ru.netology.cloud_service.model.dtos.response.JwtResponse;
//import ru.netology.cloud_service.model.entities.Role;
//import ru.netology.cloud_service.model.entities.StorageFile;
//import ru.netology.cloud_service.model.entities.User;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class TestData {
//    //Тестовый USER
//    public static final Role ROLE_USER = new Role("ROLE_USER");
//    public static final List<Role> ONE_ROLE_USER = List.of(ROLE_USER);
//    public static final String USERNAME = "Username";
//    public static final String PASSWORD = "Password";
//    public static final User USER = new User(USERNAME, PASSWORD, ONE_ROLE_USER);
//
//    //Тестовый FILE
//    public static final String FILE_NAME_1 = "FileName";
//    public static final String NEW_FILE_NAME_1 = "NewFileName";
//    public static final Long SIZE = 10L;
//    public static final byte[] FILE_CONTENT = FILE_NAME_1.getBytes();
//    public static final LocalDateTime DATA_TIME = LocalDateTime.now();
//    public static final StorageFile STORAGE_FILE_1 = StorageFile.builder()
//            .fileName(FILE_NAME_1)
//            .fileContent(FILE_CONTENT)
//            .fileSize(SIZE)
//            .dateAndTime(DATA_TIME)
//            .user(USER)
//            .build();
//
//    //Тестовый FILE_2
//    public static final String FILE_NAME_2 = "FileName2";
//    public static final byte[] FILE_CONTENT_2 = FILE_NAME_2.getBytes();
//    public static final StorageFile STORAGE_FILE_2 = StorageFile.builder()
//            .fileName(FILE_NAME_2)
//            .fileContent(FILE_CONTENT_2)
//            .fileSize(SIZE)
//            .dateAndTime(DATA_TIME)
//            .user(USER)
//            .build();
//    public static final String TOKEN = "TOKEN";
//    public static final String AUTH_TOKEN = "Bearer TOKEN";
//    public static final JwtRequest AUTH_RQ = new JwtRequest(USERNAME, PASSWORD);
//    public static final JwtResponse AUTH_RS = new JwtResponse(TOKEN);
//    public static final MultipartFile MULTIPART_FILE_1 = new MockMultipartFile(FILE_NAME_1, FILE_CONTENT);
//
//}
