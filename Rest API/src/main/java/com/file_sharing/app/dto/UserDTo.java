package com.file_sharing.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.file_sharing.app.entity.Providers;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.util.List;

/**
 * Data Transfer Object (DTO) for user-related information.
 */
=======

import java.util.List;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTo {
<<<<<<< HEAD
    
    /** Unique identifier for the user. */
    private String userId;

    /** User's password. Must be at least 4 characters long and cannot be blank. */
    @NotBlank(message = "Password is required")
    @Size(min = 4, message = "Minimum 4 characters")
    private String password;

    /** User's name. Must be at least 3 characters long and cannot be blank. */
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Required minimum 3 characters")
    private String name;

    /** User's email address. Must be in a valid email format and cannot be blank. */
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    /** User's phone number. Must be exactly 10 digits long and cannot be blank. */
    @NotBlank(message = "Phone number is required")
    @Size(max = 10, min = 10, message = "Invalid")
    private String phone;

    /** User's residential address. Cannot be blank. */
    @NotBlank(message = "Address is required")
    private String address;

    /** User's gender. Cannot be blank. */
    @NotBlank(message = "Gender is required")
    private String gender;

    /** URL for the user's profile picture (optional). */
    private String profilePic;

    /** Short bio or description about the user (optional). */
    private String about;

    /** Indicates whether the user's account is enabled or disabled. */
    private boolean enabled;

    /** Provider information associated with the user's account (e.g., Google, Facebook). */
    private Providers providers;

    /** List of files uploaded by the user. */
    private List<FileDTO> file;

    /** List of roles assigned to the user. */
=======
    private String userId;
    @NotBlank(message = "Password is required")
    @Size(min = 4,message = "Minimum 4 character")
    private String password;
    @NotBlank(message = "Name is required")
    @Size(min = 3,message = "Required minimum 3 character")
    private String name;
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotBlank(message = "Phone number is required")
    @Size(max = 10,min = 10,message = "Invalid")
    private String phone;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Gender is required")
    private String gender;
    private String profilePic;
    private String about;
    private boolean enabled;
    private Providers providers;
    private List<FileDTO> file;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    private List<RoleDTo> roles;
}
