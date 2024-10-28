package com.file_sharing.app.entity;

import jakarta.persistence.*;
import lombok.*;
<<<<<<< HEAD

import java.time.Instant;

/**
 * Entity representing a refresh token for user authentication.
 */
=======
import org.springframework.security.core.userdetails.User;

import java.time.Instant;

>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class RefreshToken {
<<<<<<< HEAD
    /** Unique identifier for the refresh token. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** The refresh token string used to obtain a new access token. */
    private String refreshTokenHold;

    /** The expiration date of the refresh token. */
    private Instant expiresDate;

    /** The user associated with the refresh token. */
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String refreshTokenHold;
    private Instant expiresDate;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    @OneToOne
    private UserEntity user;
}
