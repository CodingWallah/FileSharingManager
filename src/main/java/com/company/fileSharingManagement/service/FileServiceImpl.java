package com.company.fileSharingManagement.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.fileSharingManagement.entity.FileEntity;
import com.company.fileSharingManagement.exception.FileNotFoundException;
import com.company.fileSharingManagement.model.FileModel;
import com.company.fileSharingManagement.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {   
    
    @Autowired
    private FileRepository fileRepository;

 

    @Override
    public ResponseEntity<?> uploadFile(MultipartFile file, String uploadedBy) throws IOException {
     

        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setUploadedBy(uploadedBy);
        fileEntity.setUploadTime(LocalDateTime.now());
        fileEntity.setExpiryTime(LocalDateTime.now().plusDays(1)); // 24 hours expiry
        fileEntity.setFileData(file.getBytes());
        fileRepository.save(fileEntity);
        FileModel fileModel = new FileModel();
        BeanUtils.copyProperties(fileEntity, fileModel);
        return ResponseEntity.ok().body(fileModel);
    }
    @Override
    public ResponseEntity<?> getFile(int id)  {
        Optional<FileEntity> fileEntityOptional = fileRepository.findById(id);

        if (fileEntityOptional.isPresent()) {
            FileEntity fileEntity = fileEntityOptional.get();
            FileModel fileModel = new FileModel();
            BeanUtils.copyProperties(fileEntity, fileModel);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                    .body(fileModel.getFileData());
        } else {
            throw new FileNotFoundException("File not found");
        }
    }
    @Override
    public ResponseEntity<?> deleteFile(int id) {

        Optional <FileEntity> entity = fileRepository.findById(id);
        if(entity.isPresent()){
            fileRepository.delete(entity.get());
            return ResponseEntity.ok().body("Deleted successfully");
        }
        else{
            throw new FileNotFoundException("File not found");
        }

    }
    @Override
    @Scheduled(cron = "0 0 * * * *") 
    public void deleteExpiredFiles() {
        List<FileEntity> expiredFiles = fileRepository.findByExpiryTimeBefore(LocalDateTime.now());
        expiredFiles.forEach(fileRepository::delete);
        System.out.println("Deleted expired files at: " + LocalDateTime.now());
    }

    private FileModel convertToModel(FileEntity entity) {
        FileModel model = new FileModel();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

    
    @Override
    public List<FileModel> getAllFiles() {

        List<FileEntity> entityList = fileRepository.findAll();
        return entityList.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> shareFile(int id) {
        Optional <FileEntity> fileEntity = fileRepository.findById(id);
        if(fileEntity.isPresent()){
            FileEntity file = fileEntity.get();
            FileModel fileModel = new FileModel();
            BeanUtils.copyProperties(file, fileModel);
            return ResponseEntity.ok().body(fileModel);
 }
 else{
     throw new FileNotFoundException("File not found");
 }
}
    
}
