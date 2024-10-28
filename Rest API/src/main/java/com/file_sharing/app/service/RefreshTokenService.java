package com.file_sharing.app.service;

import com.file_sharing.app.dto.RefreshTokenDTO;
import com.file_sharing.app.dto.UserDTo;

public interface RefreshTokenService {

    //Creates a new refresh token for a given user.
    RefreshTokenDTO createRefreshToken(String userName);

    // Retrieves a refresh token by its token string.
    RefreshTokenDTO findByToken(String token);

   // Verifies if a refresh token is valid or expired.
    RefreshTokenDTO verifyRefreshToken(RefreshTokenDTO token);


    // Retrieves the user associated with a given refresh token.
    UserDTo getUserByToken(RefreshTokenDTO token);
}
