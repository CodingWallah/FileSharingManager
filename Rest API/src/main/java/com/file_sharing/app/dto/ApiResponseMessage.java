package com.file_sharing.app.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponseMessage {
<<<<<<< HEAD
    private String message;       // Custom message describing the result of the operation
    private boolean success;       // Indicator of success or failure
    private HttpStatus httpStatus; // HTTP status code for the response
=======
    private String message;
    private boolean success;
    private HttpStatus httpStatus;
>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
}
