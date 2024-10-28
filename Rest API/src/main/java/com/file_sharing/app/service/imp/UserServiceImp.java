package com.file_sharing.app.service.imp;

import com.file_sharing.app.dto.PageableResponse;
import com.file_sharing.app.dto.UserDTo;
import com.file_sharing.app.entity.FileEntity;
import com.file_sharing.app.entity.Role;
import com.file_sharing.app.entity.UserEntity;
import com.file_sharing.app.exceptions.ResourceNotFoundException;
import com.file_sharing.app.helper.AppCon;
import com.file_sharing.app.helper.Helper;
import com.file_sharing.app.repositories.RolesRepository;
import com.file_sharing.app.repositories.UserRepository;
import com.file_sharing.app.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final Logger logger= LoggerFactory.getLogger(UserServiceImp.class);
    public UserServiceImp(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
    }
    @Override
    public UserDTo createUser(UserDTo userDTO) {
        Role role1 = rolesRepository.findByRoleName("ROLE_" + AppCon.ROLE_NORMAL).orElse(null);
        if (role1 == null) {
            role1 = new Role();
            role1.setRoleId(UUID.randomUUID().toString());
            role1.setRoleName("ROLE_" + AppCon.ROLE_NORMAL);
            rolesRepository.save(role1);
        }
        UserEntity userEntity =UserEntity.builder()
                .about(userDTO.getAbout())
                .name(userDTO.getName())
                .file(null)
                .userId(UUID.randomUUID().toString())
                .phone(userDTO.getPhone())
                .address(userDTO.getAddress())
                .enabled(true)
                .gender(userDTO.getGender())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .profilePic(userDTO.getProfilePic())
                .providers(userDTO.getProviders())
                .email(userDTO.getEmail())
                .build();
        userEntity.setRoles(List.of(role1));
        UserEntity user= userRepository.save(userEntity);
        UserDTo userdto= modelMapper.map(user, UserDTo.class);
        logger.info("User created {}",userdto.toString());
        return userdto;
    }

    @Override
    public UserDTo updateUser(UserDTo userDTO, String userId) {
        UserEntity userEntity =userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        if (userDTO.getName()!=null) {
            userEntity.setName(userDTO.getName());
        }
        if (userDTO.getEmail()!=null) {
            userEntity.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword()!=null) {
            if (!userEntity.getPassword().equals(userDTO.getPassword())) {
                userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
        }
        if (userDTO.getGender()!=null) {

            userEntity.setGender(userDTO.getGender());
        }
        if (userDTO.getPhone()!=null) {
            userEntity.setPhone(userDTO.getPhone());
        }
        if(userDTO.getAddress()!=null) {
            userEntity.setAddress(userDTO.getAddress());
        }
        if (userDTO.getAbout()!=null) {
            userEntity.setAbout(userDTO.getAbout());
        }
        if (userDTO.getRoles()!=null) {
            userEntity.setEnabled(userDTO.isEnabled());
        }
        if (userDTO.getProfilePic()!=null) {
            userEntity.setProfilePic(userDTO.getProfilePic());
        }
        if (userDTO.getFile()!=null) {
            userEntity.setFile(List.of(modelMapper.map(userDTO.getFile(), FileEntity.class)));
        }
        UserEntity userEntity1 = userRepository.save(userEntity);

        return modelMapper.map(userEntity1, UserDTo.class);
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        userRepository.delete(user);
    }

    @Override
    public UserDTo getUser(String userId) {
        UserEntity user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        logger.info(user.toString());
        return modelMapper.map(user, UserDTo.class);
    }

    @Override
    public UserDTo getUserByEmail(String email) {
        return modelMapper.map(userRepository.findByEmail(email), UserDTo.class);
    }

    @Override
    public PageableResponse<UserDTo> getAllUsers(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Page<UserEntity> page=userRepository.findAll(PageRequest.of(
          pageNumber,pageSize,sortDir.equalsIgnoreCase("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending()
        ));
        return Helper.pageableResponse(page, UserDTo.class);
    }

    @Override
    public PageableResponse<UserDTo> search(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Page<UserEntity> page=userRepository.findByNameContaining(keyword,PageRequest.of(
                pageNumber,pageSize,sortDir.equalsIgnoreCase("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending()
        ));
        return Helper.pageableResponse(page, UserDTo.class);
    }
}
