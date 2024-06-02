package com.example.catalogservice.repository;

import com.example.catalogservice.entity.BookCatalogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCatalogRepository extends CrudRepository<BookCatalogEntity, Long> {
    BookCatalogEntity findByBookId(String bookId);
}
