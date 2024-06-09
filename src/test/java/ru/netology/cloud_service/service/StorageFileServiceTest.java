//package ru.netology.cloud_service.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import ru.netology.cloud_service.exception.BadCredentialsException;
//import ru.netology.cloud_service.exception.DeleteFileException;
//import ru.netology.cloud_service.exception.InputDataException;
//import ru.netology.cloud_service.exception.UploadFileException;
//import ru.netology.cloud_service.model.dtos.request.FileNameEditRequest;
//import ru.netology.cloud_service.model.entities.StorageFile;
//import ru.netology.cloud_service.repository.StorageFileRepository;
//import ru.netology.cloud_service.repository.UserRepository;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static ru.netology.cloud_service.TestData.*;
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//class StorageFileServiceTest {
//    @InjectMocks
//    private StorageFileService fileService;
//
//    @Mock
//    private  StorageFileRepository storageFileRepository;
//    @Mock
//    private  UserRepository userRepository;
//    @Mock
//    private  AuthenticationService authenticationService;
//
//    @BeforeEach
//    void setUp() {
//        Mockito.when(authenticationService.getUsernameByToken(TOKEN)).thenReturn(USERNAME);
//        Mockito.when(userRepository.findByUserName(USERNAME)).thenReturn(Optional.of(USER));
//    }
//
//    @Test
//    void fileUpload() throws IOException {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(null);
//        try (MockedStatic<LocalDateTime> theMock = Mockito.mockStatic(LocalDateTime.class)) {
//            theMock.when(LocalDateTime::now).thenReturn(DATA_TIME);
//            fileService.fileUpload(AUTH_TOKEN, FILE_NAME_1, MULTIPART_FILE_1);
//            Mockito.verify(storageFileRepository, Mockito.times(1)).save(StorageFile.builder()
//                    .fileName(FILE_NAME_1)
//                    .dateAndTime(DATA_TIME)
//                    .fileSize(MULTIPART_FILE_1.getSize())
//                    .fileContent(MULTIPART_FILE_1.getBytes())
//                    .user(USER)
//                    .build());
//        }
//    }
//    @Test
//    void BadCredentialsExceptionFileUpload() {
//        assertThrows(BadCredentialsException.class, () -> fileService.fileUpload(TOKEN, FILE_NAME_1, MULTIPART_FILE_1));
//    }
//
//    @Test
//    void AlreadyUploadFileExceptionFileUpload() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(STORAGE_FILE_1);
//        assertThrows(InputDataException.class, () -> fileService.fileUpload(AUTH_TOKEN, FILE_NAME_1, MULTIPART_FILE_1));
//    }
//
//    @Test
//    void fileDelete() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(STORAGE_FILE_1);
//        assertThrows(DeleteFileException.class, () -> fileService.fileDelete(AUTH_TOKEN, FILE_NAME_1));
//        Mockito.verify(storageFileRepository, Mockito.times(1))
//                .deleteByUserAndFileName(USER, FILE_NAME_1);
//    }
//
//    @Test
//    void BadCredentialsFileDelete() {
//        assertThrows(BadCredentialsException.class, () -> fileService.fileDelete(TOKEN, FILE_NAME_1));
//    }
//
//    @Test
//    void InputDataExceptionFileDelete() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(null);
//        assertThrows(InputDataException.class, () -> fileService.fileDelete(AUTH_TOKEN, FILE_NAME_1));
//    }
//
//    @Test
//    void fileDownload() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(STORAGE_FILE_1);
//        assertEquals(FILE_CONTENT, fileService.fileDownload(AUTH_TOKEN, FILE_NAME_1));
//    }
//
//    @Test
//    void BadCredentialsFileDownload() {
//        assertThrows(BadCredentialsException.class, () -> fileService.fileDownload(TOKEN, FILE_NAME_1));
//    }
//
//    @Test
//    void InputDataExceptionFileDownload() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(null);
//        assertThrows(InputDataException.class, () -> fileService.fileDownload(AUTH_TOKEN, FILE_NAME_1));
//    }
//
//    @Test
//    void fileNameEdit() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(STORAGE_FILE_1);
//        assertThrows(UploadFileException.class, () -> fileService
//                .fileNameEdit(AUTH_TOKEN, FILE_NAME_1, new FileNameEditRequest(NEW_FILE_NAME_1)));
//        Mockito.verify(storageFileRepository, Mockito.times(1))
//                .editFileNameByUser(USER, FILE_NAME_1, NEW_FILE_NAME_1);
//    }
//
//    @Test
//    void BadCredentialsFileNameEdit() {
//        assertThrows(BadCredentialsException.class, () -> fileService
//                .fileNameEdit(TOKEN, FILE_NAME_1,new FileNameEditRequest(NEW_FILE_NAME_1)));
//    }
//
//    @Test
//    void InputDataExceptionFileNameEdit() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(null);
//        assertThrows(InputDataException.class, () -> fileService
//                .fileNameEdit(AUTH_TOKEN, FILE_NAME_1, new FileNameEditRequest(NEW_FILE_NAME_1)));
//    }
//
//    @Test
//    void getAllFiles() {
//        fileService.getAllFiles(AUTH_TOKEN, 5);
//        Mockito.verify(storageFileRepository, Mockito.times(1))
//                .findAllByUser(USER);
//    }
//
//    @Test
//    void BadCredentialsGetAllFiles() {
//        assertThrows(BadCredentialsException.class, () -> fileService
//                .getAllFiles(TOKEN, 1));
//    }
//}