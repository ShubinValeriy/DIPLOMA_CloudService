package ru.netology.cloud_service.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.cloud_service.exception.BadCredentialsException;
import ru.netology.cloud_service.exception.DeleteFileException;
import ru.netology.cloud_service.exception.InputDataException;
import ru.netology.cloud_service.exception.UploadFileException;
import ru.netology.cloud_service.logger.LogStatus;
import ru.netology.cloud_service.logger.Logger;
import ru.netology.cloud_service.logger.SimpleLogger;
import ru.netology.cloud_service.model.entities.StorageFile;
import ru.netology.cloud_service.model.entities.User;
import ru.netology.cloud_service.model.dtos.request.FileNameEditRequest;
import ru.netology.cloud_service.model.dtos.response.ResponseForGetAllFiles;
import ru.netology.cloud_service.repository.StorageFileRepository;
import ru.netology.cloud_service.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StorageFileService {
    private final Logger logger = SimpleLogger.getInstance();
    private final StorageFileRepository storageFileRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;


    // Метод для загрузки файла в хранилище
    @Transactional
    public void fileUpload(String authToken, String fileName, MultipartFile fileContent) {
        //получаем User из репозитория залогиненых пользователей чтобы не пропустить разлогиненые живые токены
        String errorMsg = "Save Error";
        User user = getUserByToken(authToken, errorMsg);
        //Запись в БД
        try {
            //Проверим, что у нашего User уже не записан такой файл
            if (storageFileRepository.findByUserAndFileName(user, fileName) != null) {
                String errorMsgUpload = String.format("Save Error: File |'%s'| from User {'%s'} " +
                        "is already in the database", fileName, user.getUserName());
                logger.log(LogStatus.ERROR, errorMsgUpload);
                throw new InputDataException(errorMsgUpload);
            }
            // Пробуем записать файл в БД
            storageFileRepository.save(StorageFile.builder()
                    .fileName(fileName)
                    .dateAndTime(LocalDateTime.now())
                    .fileSize(fileContent.getSize())
                    .fileContent(fileContent.getBytes())
                    .user(user)
                    .build());
            logger.log(
                    LogStatus.INFO,
                    String.format("File |'%s'| from User {'%s'} success upload", fileName, user.getUserName())
            );
        } catch (IOException e) {
            String errorMsgUpload = "Save Error: Invalid input data. File not uploaded";
            logger.log(LogStatus.ERROR, errorMsgUpload);
            throw new InputDataException(errorMsgUpload);
        }
    }

    // Метод для удаления файла в хранилище
    @Transactional
    public void fileDelete(String authToken, String fileName) {
        //получаем User из репозитория залогиненых пользователей чтобы не пропустить разлогиненые живые токены
        String errorMsg = "Delete Error";
        User user = getUserByToken(authToken, errorMsg);
        //Проверяем наличия файла в БД
        String errorMsgNotFound = String.format("Delete Error: File |'%s'| from User {'%s'} not found in DataBase;",
                fileName, user.getUserName());
        checkFile(storageFileRepository.findByUserAndFileName(user, fileName), errorMsgNotFound);
        // Пробуем удалить и проверяем, что файл удален
        storageFileRepository.deleteByUserAndFileName(user, fileName);
        if (storageFileRepository.findByUserAndFileName(user, fileName) != null) {
            String msg = String.format("Delete Error: File |'%s'| from User {'%s'} wasn't delete;",
                    fileName, user.getUserName());
            logger.log(LogStatus.ERROR, msg);
            throw new DeleteFileException(msg);
        }
        logger.log(
                LogStatus.INFO,
                String.format("File |'%s'| from User {'%s'} success Delete;", fileName, user.getUserName())
        );
    }

    // Метод для скачивания файла
    public byte[] fileDownload(String authToken, String fileName) {
        //получаем User из репозитория залогиненых пользователей чтобы не пропустить разлогиненые живые токены
        String errorMsg = "Download Error";
        User user = getUserByToken(authToken, errorMsg);
        //Проверяем наличие файла в БД
        StorageFile file = storageFileRepository.findByUserAndFileName(user, fileName);
        String errorMsgDownload = String.format("Download Error: File |'%s'| from User {'%s'} " +
                "not found in DataBase;", fileName, user.getUserName());
        checkFile(file, errorMsgDownload);
        logger.log(
                LogStatus.INFO,
                String.format("User {'%s'} download File |'%s'|;", fileName, user.getUserName())
        );
        return file.getFileContent();
    }

    // Метод для изменения имени файла
    @Transactional
    public void fileNameEdit(String authToken, String fileName, FileNameEditRequest fileNameEditRequest) {
        //получаем User из репозитория залогиненых пользователей чтобы не пропустить разлогиненые живые токены
        String errorMsg = "FileNameEdit Error";
        User user = getUserByToken(authToken, errorMsg);
        // Проверим есть ли fileName для данного User в ханилище и что нет файла с новым именем
        String errorMsgFileNameEdit = String.format("FileNameEdit Error: File |'%s'| from User {'%s'} " +
                "not found in DataBase;", fileName, user.getUserName());
        checkFile(storageFileRepository.findByUserAndFileName(user, fileName), errorMsgFileNameEdit);
        if (storageFileRepository.findByUserAndFileName(user, fileNameEditRequest.getNewFileName()) != null) {
            throw new BadCredentialsException(String.format("FileNameEdit Error: New FileName |'%s'| " +
                            "from User {'%s'} is already in the database;",
                    fileNameEditRequest.getNewFileName(), user.getUserName()));
        }
        // Изменим Имя
        storageFileRepository.editFileNameByUser(user, fileName, fileNameEditRequest.getNewFileName());
        // Проверим, что файл изменил имя в БД
        if (
                storageFileRepository.findByUserAndFileName(user, fileName) != null
                        || storageFileRepository.findByUserAndFileName(
                        user, fileNameEditRequest.getNewFileName()
                ) == null
        ) {
            String msg = String.format("FileNameEdit Error: File |'%s'| from User {'%s'}" +
                    " wasn't edit;", fileName, user.getUserName());
            logger.log(LogStatus.ERROR, msg);
            throw new UploadFileException(msg);
        }
        logger.log(
                LogStatus.INFO,
                String.format("User {'%s'} edit fileName of File |'%s'| on new fileName |'%s'|",
                        user.getUserName(), fileName, fileNameEditRequest.getNewFileName())
        );
    }

    // Метод получения всего списка хранящихся данных
    public List<ResponseForGetAllFiles> getAllFiles(String authToken, Integer limit) {
        //получаем User из репозитория залогиненых пользователей чтобы не пропустить разлогиненые живые токены
        String errorMsg = "GetAllFiles Error";
        User user = getUserByToken(authToken, errorMsg);

        logger.log(
                LogStatus.INFO,
                String.format("User {'%s'} get list all files", user.getUserName())
        );
        return storageFileRepository.findAllByUser(user).stream()
                .map(o -> new ResponseForGetAllFiles(o.getFileName(), o.getFileSize()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    private void checkFile(StorageFile file, String errorMsg) {
        if (file == null) {
            logger.log(LogStatus.ERROR, errorMsg);
            throw new InputDataException(errorMsg);
        }
    }

    //Метод получения User из БД залогиненных User
    private User getUserByToken(String authToken, String errorMsg) {
        String errorMsgLogin = errorMsg + ": Invalid token - user isn't logged.";
        if (authToken.startsWith("Bearer ")) {
            final String jwtToken = authToken.substring(7);
            final String username = authenticationService.getUsernameByToken(jwtToken);
            return userRepository.findByUserName(username).orElseThrow(() -> {
                logger.log(LogStatus.ERROR, errorMsgLogin);
                return new BadCredentialsException(errorMsgLogin);
            });
        }
        logger.log(LogStatus.ERROR, errorMsgLogin);
        throw new BadCredentialsException(errorMsgLogin);
    }
}
