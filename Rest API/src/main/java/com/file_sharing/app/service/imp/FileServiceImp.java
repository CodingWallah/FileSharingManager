package com.file_sharing.app.service.imp;

import com.file_sharing.app.dto.FileDTO;
import com.file_sharing.app.dto.PageableResponse;
import com.file_sharing.app.dto.ResponseFileData;
import com.file_sharing.app.entity.FileEntity;
import com.file_sharing.app.entity.UserEntity;
import com.file_sharing.app.exceptions.FileExceptions;
import com.file_sharing.app.exceptions.ResourceNotFoundException;
import com.file_sharing.app.helper.Helper;
import com.file_sharing.app.repositories.FileRepository;
import com.file_sharing.app.repositories.UserRepository;
import com.file_sharing.app.service.FileService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImp implements FileService {
    private FileRepository fileRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private Logger logger= LoggerFactory.getLogger(FileServiceImp.class);
    public FileServiceImp(FileRepository fileRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.fileRepository = fileRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    // Save file
    @Override
    public FileDTO saveFile(MultipartFile file, String userID) {
        UserEntity userEntity = userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("User Not Found"));

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
                if (fileName.contains("..")) {
                    throw new FileExceptions("File name contains invalid path sequence");
                }
                FileEntity fileEntity =new  FileEntity();
                fileEntity.setFileId(UUID.randomUUID().toString());
                fileEntity.setFileName(fileName);
                fileEntity.setFileType(file.getContentType());
                fileEntity.setFileData(file.getBytes());
                fileEntity.setUploadBy(userEntity);
                fileEntity.setUploadDate(Instant.now());
                fileEntity.setExpiryDate(Instant.now().plusSeconds(24*60*60));// Set expiry to 1 day
                FileEntity file1=  fileRepository.save(fileEntity);
                return modelMapper.map(file1, FileDTO.class); // Convert to DTO and return
        }catch (Exception e) {
            throw new FileExceptions(e.getMessage());
        }
    }
    // Delete file
    @Override
    public void deleteFile(String fileID) {
       FileEntity fileEntity= fileRepository.findById(fileID).orElseThrow(()->new ResourceNotFoundException("File Not Found"));
        fileRepository.delete(fileEntity);
    }
    // Auto delete expired files
    @Scheduled(cron = "0 0 * * * *") // Runs every hour
    @Override
    public void deleteFilesAuto() {
        List<FileEntity> fileEntities= fileRepository.findAllByExpiryDateBefore(Instant.now());
        fileEntities.forEach(fileRepository::delete); // Delete each expired file
        logger.info("Deleted expired files at : " + Instant.now());
    }
    // Update file name
    @Override
    public FileDTO updateFile(String fileID, String Name) {
        FileEntity fileEntity= fileRepository.findById(fileID).orElseThrow(()->new ResourceNotFoundException("File Not Found"));
        fileEntity.setFileName(Name); // Update the file name
        FileEntity file1=  fileRepository.save(fileEntity);
        return modelMapper.map(file1, FileDTO.class); // Return updated file DTO
    }
    // Download file
    @Override
    public FileDTO downloadFile(String fileId) {
        FileEntity fileEntity=fileRepository.findById(fileId).orElseThrow(()->new ResourceNotFoundException("File Not Found"));
//        logger.info(fileEntity.toString());
        return modelMapper.map(fileEntity, FileDTO.class); // Return file DTO
    }
    // Get all files
    @Override
    public PageableResponse<ResponseFileData> getAllFiles(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Page<FileEntity> fileEntityPage = fileRepository.findAll(PageRequest.of(
                pageNumber, pageSize, sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()));
        return Helper.pageableFileResponse(fileEntityPage); // Return paginated response
    }
    // Get all files by user
    @Override
    public PageableResponse<ResponseFileData> getAllFilesByUser(int pageNumber, int pageSize, String sortBy, String sortDir, String userID) {
        UserEntity user= userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        Page<FileEntity> fileEntityPage = fileRepository.findAllByUploadBy(user,PageRequest.of(
                pageNumber, pageSize, sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()));
        return Helper.pageableFileResponse(fileEntityPage); // Return paginated response
    }
    // Search files
    @Override
    public PageableResponse<ResponseFileData> searchFiles(String fileName, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Page<FileEntity> fileEntityPage = fileRepository.findByFileNameContaining(fileName, PageRequest.of(
                pageNumber, pageSize, sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()));
        return Helper.pageableFileResponse(fileEntityPage); // Return paginated response
    }
}
