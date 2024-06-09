//package ru.netology.cloud_service.service;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//import ru.netology.cloud_service.exception.DeleteFileException;
//import ru.netology.cloud_service.exception.InputDataException;
//import ru.netology.cloud_service.exception.UploadFileException;
//import ru.netology.cloud_service.model.entities.StorageFile;
//import ru.netology.cloud_service.repository.StorageFileRepository;
//
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//
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
//
//    @Test
//    void fileUpload() throws IOException {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(null);
//        try (MockedStatic<LocalDateTime> theMock = Mockito.mockStatic(LocalDateTime.class)) {
//            theMock.when(LocalDateTime::now).thenReturn(DATA_TIME);
//            fileService.fileUpload(USER, FILE_NAME_1, MULTIPART_FILE_1);
//            Mockito.verify(storageFileRepository, Mockito.times(1)).save(StorageFile.builder()
//                    .fileName(FILE_NAME_1)
//                    .dateAndTime(DATA_TIME)
//                    .fileSize(MULTIPART_FILE_1.getSize())
//                    .fileContent(MULTIPART_FILE_1.getBytes())
//                    .user(USER)
//                    .build());
//        }
//    }
//
//
//    @Test
//    void AlreadyUploadFileExceptionFileUpload() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(STORAGE_FILE_1);
//        assertThrows(InputDataException.class, () -> fileService.fileUpload(USER, FILE_NAME_1, MULTIPART_FILE_1));
//    }
//
//    @Test
//    void fileDelete() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(STORAGE_FILE_1);
//        assertThrows(DeleteFileException.class, () -> fileService.fileDelete(USER, FILE_NAME_1));
//        Mockito.verify(storageFileRepository, Mockito.times(1))
//                .deleteByUserAndFileName(USER, FILE_NAME_1);
//    }
//
//    @Test
//    void InputDataExceptionFileDelete() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(null);
//        assertThrows(InputDataException.class, () -> fileService.fileDelete(USER, FILE_NAME_1));
//    }
//
//    @Test
//    void fileDownload() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(STORAGE_FILE_1);
//        assertEquals(STORAGE_FILE_1, fileService.fileDownload(USER, FILE_NAME_1));
//    }
//
//    @Test
//    void InputDataExceptionFileDownload() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(null);
//        assertThrows(InputDataException.class, () -> fileService.fileDownload(USER, FILE_NAME_1));
//    }
//
//    @Test
//    void fileNameEdit() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(STORAGE_FILE_1);
//        assertThrows(UploadFileException.class, () -> fileService
//                .fileNameEdit(USER, FILE_NAME_1, NEW_FILE_NAME_1));
//        Mockito.verify(storageFileRepository, Mockito.times(1))
//                .editFileNameByUser(USER, FILE_NAME_1, NEW_FILE_NAME_1);
//    }
//
//    @Test
//    void InputDataExceptionFileNameEdit() {
//        Mockito.when(storageFileRepository.findByUserAndFileName(USER, FILE_NAME_1))
//                .thenReturn(null);
//        assertThrows(InputDataException.class, () -> fileService
//                .fileNameEdit(USER, FILE_NAME_1, NEW_FILE_NAME_1));
//    }
//
//    @Test
//    void getAllFiles() {
//        fileService.getAllFiles(USER);
//        Mockito.verify(storageFileRepository, Mockito.times(1))
//                .findAllByUser(USER);
//    }
//}