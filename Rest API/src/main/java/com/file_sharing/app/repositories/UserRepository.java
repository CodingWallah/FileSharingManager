package com.file_sharing.app.repositories;

import com.file_sharing.app.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
<<<<<<< HEAD

    /**
     * Finds a user entity by its email.
     *
     * @param email the email of the user to find
     * @return an Optional containing the found UserEntity, or empty if not found
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Finds users whose names contain the specified keyword.
     *
     * @param keyword the keyword to search for in user names
     * @param pageable the pagination information
     * @return a page of UserEntities containing the matching users
     */
    Page<UserEntity> findByNameContaining(String keyword, Pageable pageable);

    /**
     * Finds a user entity by email and password.
     *
     * @param email    the email of the user to find
     * @param password the password of the user to find
     * @return an Optional containing the found UserEntity, or empty if not found
     * 
     * Note: For security reasons, consider handling password verification differently.
     */
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
=======
    Optional<UserEntity> findByEmail(String email);
    Page<UserEntity>  findByNameContaining(String keyword, Pageable pageable);
    Optional<UserEntity> findByEmailAndPassword(String email,String password);

>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
}
