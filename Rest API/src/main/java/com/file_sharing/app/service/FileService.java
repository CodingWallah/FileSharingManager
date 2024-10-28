package com.file_sharing.app.service;

import com.file_sharing.app.dto.FileDTO;
import com.file_sharing.app.dto.PageableResponse;
import com.file_sharing.app.dto.ResponseFileData;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {



    // Method to save an uploaded file
    // Takes the file and the user ID as parameters
    FileDTO saveFile(MultipartFile file, String userID);
    // Method to delete a file based on its ID
    void deleteFile(String fileID);

    // Method to automatically delete files based on defined criteria (e.g., expiration)
    void deleteFilesAuto();

    // Method to update the name of a file based on its ID
    FileDTO updateFile(String fileID,String Name);
    // Method to download a file based on its ID
    FileDTO downloadFile(String fileId);
    // Method to get a paginated list of all files with sorting options
    PageableResponse<ResponseFileData> getAllFiles(int pageNumber, int pageSize, String sortBy, String sortDir);
    // Method to get a paginated list of files owned by a specific user with sorting options
    PageableResponse<ResponseFileData> getAllFilesByUser(int pageNumber, int pageSize, String sortBy, String sortDir, String userID);
    // Method to search for files by name, returning a paginated response
    PageableResponse<ResponseFileData> searchFiles(String fileName,int pageNumber, int pageSize, String sortBy, String sortDir);
}
