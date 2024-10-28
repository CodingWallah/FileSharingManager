package com.file_sharing.app.helper;

import com.file_sharing.app.dto.PageableResponse;
import com.file_sharing.app.dto.ResponseFileData;
import com.file_sharing.app.entity.FileEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

<<<<<<< HEAD
import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Converts a pageable entity response to a pageable DTO response.
     *
     * @param page the pageable entity response
     * @param type the class type of the DTO to convert to
     * @param <Entity> the type of the entity
     * @param <Dto> the type of the DTO
     * @return a pageable response containing the DTOs
     */
    public static <Entity, Dto> PageableResponse<Dto> pageableResponse(Page<Entity> page, Class<Dto> type) {
        List<Dto> dtoList = page.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, type)) // Map each entity to its corresponding DTO
                .collect(Collectors.toList());

=======
import java.util.ArrayList;
import java.util.List;

public class Helper {
//     * Converts a pageable entity response to a pageable DTO response.
    public static <U,V> PageableResponse<V> pageableResponse(Page<U> page, Class<V> type) {
        List<U> content = page.getContent(); // Get content from the page
        List<V> dtoList=content
                .stream()
                .map(object-> new ModelMapper().map(object, type)) // Map each entity to its corresponding DTO
                .toList();
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
        // Return a pageable response with the page details and mapped DTOs
        return new PageableResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                dtoList,
                page.isLast()
        );
    }
<<<<<<< HEAD

    /**
     * Converts a pageable response of file entities to a pageable response of file data DTOs.
     *
     * @param page the pageable response containing file entities
     * @return a pageable response containing file data DTOs
     */
    public static PageableResponse<ResponseFileData> pageableFileResponse(Page<FileEntity> page) {
        List<ResponseFileData> dtoList = page.getContent()
                .stream()
                .map(fileEntity -> {
                    // Build ResponseFileData DTO for each FileEntity
                    return ResponseFileData.builder()
                            .fileName(fileEntity.getFileName())
                            .fileSize(fileEntity.getFileData() != null ? fileEntity.getFileData().length : 0) // Handle potential null
                            .fileType(fileEntity.getFileType())
                            .fileId(fileEntity.getFileId())
                            .url(ServletUriComponentsBuilder
                                    .fromCurrentContextPath()
                                    .path("/file/files/download/")
                                    .path(fileEntity.getFileId())
                                    .toUriString())
                            .build();
                })
                .collect(Collectors.toList());

=======
//    Converts a pageable response of file entities to a pageable response of file data DTOs.
    public static  PageableResponse<ResponseFileData> pageableFileResponse(Page<FileEntity> page){
        List<FileEntity> content = page.getContent(); // Get content from the page
        List<ResponseFileData> dtoList=content
                .stream()
                .map(object->{
                    // Build ResponseFileData DTO for each FileEntity
                    return ResponseFileData.builder()
                            .fileName(object.getFileName())
                            .fileSize(object.getFileData().length)
                            .fileType(object.getFileType())
                            .fileId(object.getFileId())
                            .url(ServletUriComponentsBuilder
                                    .fromCurrentContextPath()
                                    .path("/file/files/download/")
                                    .path(object.getFileId())
                                    .toUriString())
                            .build();

                })
                .toList();
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
        // Return a pageable response with the page details and mapped file data DTOs
        return new PageableResponse<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                dtoList,
                page.isLast()
        );
    }
}
