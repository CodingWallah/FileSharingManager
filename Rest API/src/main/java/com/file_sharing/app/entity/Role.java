package com.file_sharing.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
/**
 * Entity representing a user role in the system.
 */
=======
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
<<<<<<< HEAD
    /** Unique identifier for the role. */
    @Id
    private String roleId;

    /** Name of the role (e.g., ADMIN, USER). */
    private String roleName;

    /** List of users associated with this role. */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> users = new ArrayList<>();
=======
    @Id
    private String roleId;
    private String roleName;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private List<UserEntity> users=new ArrayList<UserEntity>();
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
}
