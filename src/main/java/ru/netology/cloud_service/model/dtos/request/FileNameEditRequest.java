package ru.netology.cloud_service.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileNameEditRequest {
    private String newFileName;
}
