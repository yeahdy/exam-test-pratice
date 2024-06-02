package com.example.catalogservice.controller;

import com.example.catalogservice.common.response.ResponseMessage;
import com.example.catalogservice.service.BookCatalogService;
import com.example.catalogservice.vo.ResponseBookCatalog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book-catalog-service")
public class BookCatalogController {

    private final BookCatalogService bookCatalogService;

    @GetMapping("/health_check")
    public String status() {
        return String.format("Working in Book catalog Service!");
    }

    @GetMapping("/book/list")
    public ResponseMessage<List<ResponseBookCatalog>> getBooks() {
        List<ResponseBookCatalog> bookCatalogs = bookCatalogService.getAllBookCatalogs();
        return ResponseMessage.success(bookCatalogs, "Book catalog Enquiry");
    }

}
