package com.file_sharing.app.service;

import com.file_sharing.app.dto.PageableResponse;
import com.file_sharing.app.dto.UserDTo;

public interface UserService {
    // Method to create a new user
    UserDTo createUser(UserDTo userDTO);
    // Method to update an existing user based on their ID
    UserDTo updateUser(UserDTo userDTO, String userId);
    // Method to delete a user based on their ID
    void deleteUser(String userId);
    // Method to retrieve a single user based on their ID
    UserDTo getUser(String userId);
    // Method to get a user based on their email address
    UserDTo getUserByEmail(String email);
    // Method to get a paginated list of all users with sorting options
    PageableResponse<UserDTo> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir);
    // Method to search for users based on a keyword (e.g., username, email)
    PageableResponse<UserDTo> search(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir);

}
