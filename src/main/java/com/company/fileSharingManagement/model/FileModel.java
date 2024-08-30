package com.company.fileSharingManagement.model;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class FileModel {
    private int id;
    private String fileName;
    private String uploadedBy;
    private LocalDateTime uploadTime;
    private LocalDateTime expiryTime;
    private byte[] fileData;
}