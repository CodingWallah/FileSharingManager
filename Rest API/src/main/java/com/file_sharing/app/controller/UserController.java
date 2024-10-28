package com.file_sharing.app.controller;

<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

=======
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
import com.file_sharing.app.dto.ApiResponseMessage;
import com.file_sharing.app.dto.PageableResponse;
import com.file_sharing.app.dto.UserDTo;
import com.file_sharing.app.entity.Providers;
import com.file_sharing.app.helper.AppCon;
import com.file_sharing.app.service.UserService;
<<<<<<< HEAD

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user") // Base URL for all endpoints in this controller
public class UserController {

    // Service to handle user-related business logic
    private final UserService userService;

    // Constructor for dependency injection of UserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Enum value representing the provider type for user creation
    @Enumerated(EnumType.STRING)
    private final Providers provider = Providers.SELF;

    /**
     * Endpoint to create a new user. Sets default values for user attributes
     * and calls the userService to save the user.
     *
     * @param userDTO User data received in the request body
     * @return ResponseEntity with the created user data and status 200 OK
     */
    @PostMapping
    ResponseEntity<UserDTo> createUser(@Valid @RequestBody UserDTo userDTO) {
        userDTO.setEnabled(true); // Enable the user by default
        userDTO.setProviders(provider); // Set provider type
        UserDTo createdUser = userService.createUser(userDTO); // Create user using the service
        return ResponseEntity.ok(createdUser); // Return created user data
    }

    /**
     * Endpoint to delete a user by their ID. Calls userService to handle
     * deletion and responds with a success message.
     *
     * @param userId ID of the user to be deleted
     * @return ResponseEntity with deletion confirmation message
     */
    @DeleteMapping("/{userId}")
    ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId); // Delete user based on ID
=======
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Enumerated(EnumType.STRING)
    private final Providers provider=Providers.SELF;
    //Creates a new user.
    @PostMapping
    ResponseEntity<UserDTo> createUser(@Valid @RequestBody UserDTo userDTO) {
        userDTO.setEnabled(true);
        userDTO.setProviders(provider);
        UserDTo createdUser = userService.createUser(userDTO);

        return ResponseEntity.ok(createdUser);
    }
    // Deletes a user by ID.
    @DeleteMapping("/{userId}")
    ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
        return ResponseEntity.ok(
                ApiResponseMessage.builder()
                        .message("User Successfully deleted")
                        .httpStatus(HttpStatus.ACCEPTED)
                        .success(true)
                        .build()
        );
    }
<<<<<<< HEAD

    /**
     * Endpoint to update user information by their ID. Calls userService to
     * update the user details and returns the updated data.
     *
     * @param userDTO Updated user data received in the request body
     * @param userId ID of the user to be updated
     * @return ResponseEntity with the updated user data and status 200 OK
     */
    @PutMapping("/{userId}")
    ResponseEntity<UserDTo> updateUser(@Valid @RequestBody UserDTo userDTO, @PathVariable("userId") String userId) {
        UserDTo updatedUser = userService.updateUser(userDTO, userId); // Update user details
        return ResponseEntity.ok(updatedUser); // Return updated user data
    }

    /**
     * Endpoint to retrieve all users with pagination and sorting. Calls
     * userService to fetch paginated user data based on provided parameters.
     *
     * @param pageNumber Page number for pagination
     * @param pageSize Number of users per page
     * @param sortBy Attribute to sort by (e.g., name, date)
     * @param sortDir Sort direction (asc or desc)
     * @return ResponseEntity with pageable response of users
     */
    @GetMapping
    ResponseEntity<PageableResponse<UserDTo>> getAllUsers(
            @RequestParam(value = "pageNumber", defaultValue = AppCon.Page_Number, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppCon.Page_Size, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppCon.Sort_By, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppCon.Sort_Dir, required = false) String sortDir) {
        PageableResponse<UserDTo> pageableResponse = userService.getAllUsers(pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(pageableResponse); // Return paginated and sorted user list
    }

    /**
     * Endpoint to retrieve a specific user by their ID. Calls userService to
     * fetch the user and returns the user data.
     *
     * @param userId ID of the user to retrieve
     * @return ResponseEntity with the user's data and status 200 OK
     */
    @GetMapping("/{userId}")
    ResponseEntity<UserDTo> getUser(@PathVariable("userId") String userId) {
        UserDTo userDTO = userService.getUser(userId); // Get user details by ID
        return ResponseEntity.ok(userDTO); // Return user data
    }

    /**
     * Endpoint to search for users by a keyword, with pagination and sorting.
     * Calls userService to perform the search operation.
     *
     * @param keyword Keyword to search in user attributes
     * @param pageNumber Page number for pagination
     * @param pageSize Number of users per page
     * @param sortBy Attribute to sort by
     * @param sortDir Sort direction (asc or desc)
     * @return ResponseEntity with pageable response of search results
     */
    @GetMapping("/search")
    ResponseEntity<PageableResponse<UserDTo>> searchUsers(
            @RequestParam(value = "searchKeyword") String keyword,
            @RequestParam(value = "pageNumber", defaultValue = AppCon.Page_Number, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppCon.Page_Size, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppCon.Sort_By, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppCon.Sort_Dir, required = false) String sortDir) {
        PageableResponse<UserDTo> pageableResponse = userService.search(keyword, pageNumber, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(pageableResponse); // Return paginated search results
=======
    //Updates a user's information.
    @PutMapping("/{userId}")
    ResponseEntity<UserDTo> updateUser(@Valid @RequestBody UserDTo userDTO, @PathVariable("userId") String userId) {
        UserDTo updatedUser=userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(updatedUser);
    }
    //  Retrieves all users with pagination and sorting.
    @GetMapping
    ResponseEntity<PageableResponse<UserDTo>> getAllUsers(
            @RequestParam(value = "pageNumber",defaultValue = AppCon.Page_Number,required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppCon.Page_Size,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue=AppCon.Sort_By,required = false) String sortBy ,
            @RequestParam(value = "sortDir", defaultValue= AppCon.Sort_Dir,required = false) String sortDir) {
           PageableResponse<UserDTo> pageableResponse= userService.getAllUsers(pageNumber,pageSize,sortBy,sortDir);
           return ResponseEntity.ok(pageableResponse);
    }
    // Retrieves a specific user by ID
    @GetMapping("/{userId}")
    ResponseEntity<UserDTo> getUser(
            @PathVariable("userId") String userId,
            @RequestParam(value = "pageNumber",defaultValue = AppCon.Page_Number,required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppCon.Page_Size,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue=AppCon.Sort_By,required = false) String sortBy ,
            @RequestParam(value = "sortDir", defaultValue= AppCon.Sort_Dir,required = false) String sortDir
            )
    {
        UserDTo userDTO=userService.getUser(userId);
        return ResponseEntity.ok(userDTO);
    }
    // Searches for users by a keyword with pagination and sorting.
    @GetMapping("/search")
    ResponseEntity<PageableResponse<UserDTo>> searchUsers(
            @RequestParam(value = "searchKeyword") String keyword,
            @RequestParam(value = "pageNumber",defaultValue = AppCon.Page_Number,required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppCon.Page_Size,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue=AppCon.Sort_By,required = false) String sortBy ,
            @RequestParam(value = "sortDir", defaultValue= AppCon.Sort_Dir,required = false) String sortDir

    )
    {
     PageableResponse<UserDTo> pageableResponse= userService.search(keyword,pageNumber,pageSize,sortBy,sortDir);
     return ResponseEntity.ok(pageableResponse);
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    }
}
