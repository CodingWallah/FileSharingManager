package com.file_sharing.app.dto;

<<<<<<< HEAD
=======
import com.file_sharing.app.entity.UserEntity;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTo {
<<<<<<< HEAD

    private String roleId;            // Unique identifier for the role
    private String roleName;          // Name of the role (e.g., "Admin", "User")
    private List<UserDTo> users;      // List of users associated with this role
=======
    private String roleId;
    private String roleName;
//    private List<UserEntity> users;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
}
