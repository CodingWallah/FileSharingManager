package com.file_sharing.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
<<<<<<< HEAD

@Data
@AllArgsConstructor
public class PageableResponse<T> {
    private int pageNumber;        // The current page number
    private int pageSize;          // The number of items per page
    private long totalElements;     // The total number of elements available
    private long totalPages;        // The total number of pages available
    private List<T> content;        // The content of the current page, a list of items of type T
    private boolean lastPage;       // Indicates if the current page is the last page
=======
@Data
@AllArgsConstructor
public class PageableResponse <T>{
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private List<T> content;
    private boolean lastPage;

>>>>>>> 756502deffa4e5fbc6afc939bcdb026fc3b8f241
}
