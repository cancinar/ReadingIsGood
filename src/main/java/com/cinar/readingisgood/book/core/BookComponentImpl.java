package com.cinar.readingisgood.book.core;


import com.cinar.readingisgood.book.core.domain.BookDomain;
import com.cinar.readingisgood.book.core.entity.Book;
import com.cinar.readingisgood.base.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
@Slf4j
class BookComponentImpl implements BookComponent {

  private final BookJPARepository bookJPARepository;


  @Override
  public BookDomain addBook(String name, Long stock, Double price) {
    if (ObjectUtils.isEmpty(name) || ObjectUtils.isEmpty(stock)) {
      throw new ValidationException("Name or Stock cannot be empty.");
    }
    if (price <= 0) {
      throw new ValidationException("Price should be more than zero.");
    }

    bookJPARepository.findByNameIgnoreCase(name)
        .ifPresent(book -> {
          throw new ValidationException(book.getName() + " has already been added.");
        });

    return BookDomain
        .fromBook(bookJPARepository.save(Book.builder()
            .name(name)
            .stock(stock)
            .price(price)
            .build()));
  }

  @Override
  public BookDomain updateStock(String name, Long stock) {
    final Book book = bookJPARepository.findByNameIgnoreCase(name)
        .orElseThrow(() -> new ValidationException("Book cannot be found!"));

    book.setStock(stock);

    return BookDomain.fromBook(bookJPARepository.save(book));
  }

  @Override
  public BookDomain getBook(String name) {
    return bookJPARepository.findByNameIgnoreCase(name)
        .map(BookDomain::fromBook)
        .orElseThrow(() -> new ValidationException("Book cannot be found!"));
  }
}