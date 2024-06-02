package com.example.catalogservice.service;

import com.example.catalogservice.entity.BookCatalogEntity;
import com.example.catalogservice.repository.BookCatalogRepository;
import com.example.catalogservice.vo.ResponseBookCatalog;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookCatalogServiceImpl implements BookCatalogService {

    private final BookCatalogRepository bookCatalogRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ResponseBookCatalog> getAllBookCatalogs() {
        Iterable<BookCatalogEntity> bookCatalogEntities = bookCatalogRepository.findAll();
        List<ResponseBookCatalog> bookCatalogList = new ArrayList<>();
        bookCatalogEntities.forEach(bookCatalog ->
                bookCatalogList.add(modelMapper.map(bookCatalog,ResponseBookCatalog.class)));
        return bookCatalogList;
    }
}
