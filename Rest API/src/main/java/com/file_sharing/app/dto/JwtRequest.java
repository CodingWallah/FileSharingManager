package com.file_sharing.app.dto;

import lombok.*;

import java.io.Serializable;

<<<<<<< HEAD
=======

>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
<<<<<<< HEAD
public class JwtRequest implements Serializable {
    private String username;  // The username of the user attempting to authenticate
    private String password;  // The password for the given username
=======
public class JwtRequest {
    private String username;
    private String password;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
}
