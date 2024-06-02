package com.example.catalogservice.service;

import com.example.catalogservice.vo.ResponseBookCatalog;
import java.util.List;

public interface BookCatalogService {
    List<ResponseBookCatalog> getAllBookCatalogs();
}
