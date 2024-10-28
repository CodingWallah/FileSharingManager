package com.file_sharing.app.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.file_sharing.app.dto.JwtRequest;
import com.file_sharing.app.dto.JwtResponse;
import com.file_sharing.app.dto.RefreshTokenDTO;
import com.file_sharing.app.dto.RefreshTokenRequest;
import com.file_sharing.app.dto.UserDTo;
import com.file_sharing.app.entity.UserEntity;
import com.file_sharing.app.security.JWTHelper;
import com.file_sharing.app.service.RefreshTokenService;
import com.file_sharing.app.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTHelper jwtHelper;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JWTHelper jwtHelper,
            ModelMapper modelMapper,
            UserService userService,
            RefreshTokenService refreshTokenService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

<<<<<<< HEAD
    /**
     * Authenticates a user and generates a JWT token.
     *
     * This endpoint takes a JwtRequest object containing the user's email and
     * password, authenticates the user, and generates a JWT token along with a
     * refresh token.
     *
     * @param jwtRequest the JWTRequest containing user's login credentials
     * @return ResponseEntity containing the JWT token, refresh token, and user
     * details
     */
    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        // Authenticate the user using email and password
        this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        // Load user details by username (email in this case)
        UserEntity user = (UserEntity) this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        // Generate JWT token for authenticated user
        String token = jwtHelper.generateToken(user);

        // Create a new refresh token for the user
        RefreshTokenDTO refreshTokenDTO = refreshTokenService.createRefreshToken(user.getEmail());

        // Return response with JWT token, refresh token, and mapped user details
=======
//     * Authenticates a user and generates a JWT token.
//
//     * This endpoint takes a JWTRequest object containing the user's email and password,
//     * authenticates the user, and generates a JWT token along with a refresh token.
    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        UserEntity user = (UserEntity) this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtHelper.generateToken(user);
        RefreshTokenDTO refreshTokenDTO = refreshTokenService.createRefreshToken(user.getEmail());
        // Return response with JWT and refresh token
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
        return ResponseEntity.ok(
                JwtResponse
                        .builder()
                        .refreshToken(refreshTokenDTO)
                        .JwtToken(token)
                        .user(modelMapper.map(user, UserDTo.class))
                        .build());
    }

<<<<<<< HEAD
    /**
     * Regenerates a JWT token using a valid refresh token.
     *
     * This endpoint accepts a RefreshTokenRequest containing a refresh token,
     * verifies the token, and generates a new JWT token for the user.
     *
     * @param refreshTokenRequest the RefreshTokenRequest containing the refresh
     * token
     * @return ResponseEntity containing the new JWT token, refresh token, and
     * user details
     */
    @PostMapping("/regenerate-token")
    public ResponseEntity<JwtResponse> regenerateToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        // Retrieve refresh token details using the provided token
        RefreshTokenDTO refreshTokenDTO = refreshTokenService.findByToken(refreshTokenRequest.getRefreshToken());

        // Verify the refresh token's validity and expiration
        RefreshTokenDTO verifiedToken = refreshTokenService.verifyRefreshToken(refreshTokenDTO);

        // Retrieve user details associated with the verified token
        UserDTo userDTo = refreshTokenService.getUserByToken(verifiedToken);

        // Generate a new JWT token for the user
        String jwtToken = jwtHelper.generateToken(modelMapper.map(userDTo, UserEntity.class));

        // Return response with new JWT token, verified refresh token, and user details
        return ResponseEntity.ok(JwtResponse.builder()
                .JwtToken(jwtToken)
=======
    //Regenerates a JWT token using a valid refresh token.
    // This endpoint accepts a RefreshTokenRequest containing a refresh token,
    // verifies the token, and generates a new JWT token for the user.
    @PostMapping("/regenerate-token")
    public ResponseEntity<JwtResponse> regenerateToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshTokenDTO refreshTokenDTO = refreshTokenService.findByToken(refreshTokenRequest.getRefreshToken());
        RefreshTokenDTO refreshTokenDTO1 = refreshTokenService.verifyRefreshToken(refreshTokenDTO);
        UserDTo userDTo = refreshTokenService.getUserByToken(refreshTokenDTO1);
        String jwtToken = jwtHelper.generateToken(modelMapper.map(userDTo, UserEntity.class));
        return ResponseEntity.ok(JwtResponse.builder().JwtToken(jwtToken)
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
                .user(userDTo)
                .refreshToken(refreshTokenDTO)
                .build());
    }

<<<<<<< HEAD
    /**
     * Helper method to perform user authentication.
     *
     * This method attempts to authenticate a user based on provided email and
     * password. If authentication fails, it throws a BadCredentialsException.
     *
     * @param email the user's email
     * @param password the user's password
     */
    private void doAuthenticate(String email, String password) {
        try {
            // Attempt to authenticate with given credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            // Log and throw an exception if credentials are invalid
            logger.error("Invalid credentials for user: {}", email);
            throw new BadCredentialsException("User not found with the given username or password");
=======
    // Helper method to perform authentication
    private void doAuthenticate(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("User not found of given username or password");
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
        }
    }
}
