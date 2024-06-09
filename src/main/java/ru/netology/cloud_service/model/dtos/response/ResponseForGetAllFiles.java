package ru.netology.cloud_service.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseForGetAllFiles {
    private String fileName;
    private Long fileSizeInBytes;
}
