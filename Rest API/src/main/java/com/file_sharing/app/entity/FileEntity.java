package com.file_sharing.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

<<<<<<< HEAD
/**
 * Entity representing a file in the file sharing application.
 */
@Data
@Entity
public class FileEntity {
    
    /** Unique identifier for the file. */
    @Id
    private String fileId;

    /** Name of the file. */
    private String fileName;

    /** Type of the file (e.g., PDF, JPEG). */
    private String fileType;

    /** User who uploaded the file. */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity uploadBy;

    /** Date and time when the file was uploaded. */
    private Instant uploadDate;

    /** Date and time when the file expires, if applicable. */
    private Instant expiryDate;

    /** Binary data of the file. Stored as a LOB (Large Object). */
    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB")
=======
@Data
@Entity
public class FileEntity{
    @Id
    private String fileId;
    private String fileName;
    private String fileType;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private UserEntity uploadBy;
    private Instant uploadDate;
    private Instant expiryDate;
    @Lob
    @Column(name = "data",columnDefinition = "LONGBLOB")
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
    private byte[] fileData;
}
