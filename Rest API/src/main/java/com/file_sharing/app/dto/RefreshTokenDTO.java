package com.file_sharing.app.dto;

import lombok.*;

import java.time.Instant;
<<<<<<< HEAD

=======
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenDTO {
<<<<<<< HEAD
    private int id;                   // Unique identifier for the refresh token
    private String refreshTokenHold;  // The actual refresh token string
    private Instant expiresDate;      // Expiration date of the refresh token
    private UserDTo user;             // Associated user details
=======
    private int id;
    private String refreshTokenHold;
    private Instant expiresDate;
    private UserDTo user;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
}
