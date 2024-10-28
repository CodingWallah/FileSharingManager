package com.file_sharing.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
/**
 * Entity representing a user in the system, implementing Spring Security's UserDetails interface.
 */
=======
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
<<<<<<< HEAD
public class UserEntity implements UserDetails {
    /** Unique identifier for the user. */
    @Id
    private String userId;

    /** User's password, not exposed through getters. */
    @Getter(AccessLevel.NONE)
    private String password;

    /** User's full name. */
    private String name;

    /** User's email, must be unique and not null. */
    @Column(unique = true, nullable = false)
    private String email;

    /** User's phone number. */
    private String phone;

    /** User's address. */
    private String address;

    /** User's gender. */
    private String gender;

    /** URL or path to the user's profile picture, with a maximum length. */
    @Column(length = 1000)
    private String profilePic;

    /** User's about information, with a maximum length. */
    @Column(length = 1000)
    private String about;

    /** Indicates if the user account is enabled (active). */
    private boolean enabled = true;

    /** Provider used for the user's authentication (e.g., GOOGLE, GITHUB). */
    private Providers providers;

    /** List of files uploaded by the user. */
    @OneToMany(mappedBy = "uploadBy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FileEntity> file;

    /** List of roles assigned to the user. */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converts roles to Spring Security authorities.
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password; // Returns the user's password.
    }

    @Override
    public String getUsername() {
        return this.email; // Returns the user's email as the username.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // The account is not expired.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // The account is not locked.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // The credentials are not expired.
    }

    @Override
    public boolean isEnabled() {
        return this.enabled; // Returns if the account is enabled.
=======
public class UserEntity implements UserDetails{
    @Id
    private String userId;
    @Getter(AccessLevel.NONE)
    private String password;
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String phone;
    private String address;
    private String gender;
    @Column(length = 1000)
    private String profilePic;
    @Column(length = 1000)
    private String about;
    private boolean enabled=true;
    private Providers providers;
    @OneToMany(mappedBy = "uploadBy",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FileEntity> file;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles=new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return this.enabled;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    }
}
