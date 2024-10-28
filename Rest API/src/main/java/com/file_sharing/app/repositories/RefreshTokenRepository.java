package com.file_sharing.app.repositories;

import com.file_sharing.app.entity.RefreshToken;
import com.file_sharing.app.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
<<<<<<< HEAD

    /**
     * Finds a refresh token by its held value.
     *
     * @param refreshTokenHold the value of the refresh token to find
     * @return an Optional containing the found RefreshToken, or empty if not found
     */
    Optional<RefreshToken> findByRefreshTokenHold(String refreshTokenHold);

    /**
     * Finds a refresh token associated with a specific user.
     *
     * @param user the user associated with the refresh token
     * @return an Optional containing the found RefreshToken, or empty if not found
     */
    Optional<RefreshToken> findByUser(UserEntity user);

    /**
     * Finds all refresh tokens that have expired before the given timestamp.
     *
     * @param timestamp the timestamp to compare against the expiry dates
     * @return a list of expired RefreshTokens
     */
=======
    Optional<RefreshToken> findByRefreshTokenHold(String refreshTokenHold);
    Optional<RefreshToken> findByUser(UserEntity user);
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    List<RefreshToken> findByExpiresDateBefore(Instant timestamp);
}
