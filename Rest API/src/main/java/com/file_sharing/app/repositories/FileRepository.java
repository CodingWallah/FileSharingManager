package com.file_sharing.app.repositories;

import com.file_sharing.app.entity.FileEntity;
import com.file_sharing.app.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, String> {
<<<<<<< HEAD

    /**
     * Finds all files uploaded by a specific user, paginated.
     *
     * @param user the user who uploaded the files
     * @param pageable pagination information
     * @return a paginated list of files uploaded by the user
     */
    Page<FileEntity> findAllByUploadBy(UserEntity user, Pageable pageable);

    /**
     * Finds files whose names contain a specific string, paginated.
     *
     * @param fileName the string to search for in file names
     * @param pageable pagination information
     * @return a paginated list of files whose names contain the specified string
     */
    Page<FileEntity> findByFileNameContaining(String fileName, Pageable pageable);

    /**
     * Finds all files that have expired before a specific instant.
     *
     * @param instant the time to compare expiry dates against
     * @return a list of files that have expired
     */
=======
    Page<FileEntity> findAllByUploadBy(UserEntity user, Pageable pageable);
    Page<FileEntity> findByFileNameContaining(String fileName, Pageable pageable);
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    List<FileEntity> findAllByExpiryDateBefore(Instant instant);
}
