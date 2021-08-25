package com.cinar.readingisgood.book.core;

import com.cinar.readingisgood.book.core.entity.Book;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJPARepository extends MongoRepository<Book, Long> {

  Optional<Book> findByNameIgnoreCase(String name);
}
