package com.file_sharing.app.service.imp;

import com.file_sharing.app.dto.RefreshTokenDTO;
import com.file_sharing.app.dto.UserDTo;
import com.file_sharing.app.entity.RefreshToken;
import com.file_sharing.app.entity.UserEntity;
import com.file_sharing.app.exceptions.ResourceNotFoundException;
import com.file_sharing.app.repositories.RefreshTokenRepository;
import com.file_sharing.app.repositories.UserRepository;
import com.file_sharing.app.service.RefreshTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImp implements RefreshTokenService {
    RefreshTokenRepository refreshTokenRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;
    public RefreshTokenServiceImp(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

      //Creates a new refresh token for the given username.
     // If the user already has a refresh token, update the existing token with a new one.

    @Override
    public RefreshTokenDTO createRefreshToken(String userName) {
        // Fetch the user entity by email; throw an exception if not found
        UserEntity user=userRepository.findByEmail(userName)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        // Check if the user already has a refresh token
        RefreshToken refreshToken=refreshTokenRepository.findByUser(user).orElse(null);
        Instant now = Instant.now().plusSeconds(30*24*60*60); // Set expiration to 30 days
        // If no existing token, create a new one
        if(refreshToken==null) {
            refreshToken=RefreshToken.builder()
                    .user(user)
                    .refreshTokenHold(UUID.randomUUID().toString())
                    .expiresDate(now)
                    .build();


        }
        // If an existing token is found, update it
        else {
            refreshToken.setExpiresDate(now);
            refreshToken.setRefreshTokenHold(UUID.randomUUID().toString());//Reset Expiration
        }
        RefreshToken token=refreshTokenRepository.save(refreshToken);
        RefreshTokenDTO dto=modelMapper.map(token, RefreshTokenDTO.class);
        return dto;
    }

    @Override
    public RefreshTokenDTO findByToken(String token) {
        // Find the refresh token by its value; throw an exception if not found
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshTokenHold(token)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh Token not found"));
        return modelMapper.map(refreshToken, RefreshTokenDTO.class);

    }

    @Override
    public RefreshTokenDTO verifyRefreshToken(RefreshTokenDTO token) {
        var refreshToken = modelMapper.map(token, RefreshToken.class);

        // Check if the token has expired
        if (token.getExpiresDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new ResourceNotFoundException("Refresh Token Expired");
        }
        return token;
    }

    @Override
    public UserDTo getUserByToken(RefreshTokenDTO token) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshTokenHold(token.getRefreshTokenHold())
                .orElseThrow(() -> new ResourceNotFoundException("Refresh Token not found"));

        UserEntity user = refreshToken.getUser();
        return modelMapper.map(user, UserDTo.class);
    }
}
