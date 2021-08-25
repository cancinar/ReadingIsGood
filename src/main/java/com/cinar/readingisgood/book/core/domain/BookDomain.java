package com.cinar.readingisgood.book.core.domain;

import com.cinar.readingisgood.book.core.entity.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDomain {

  private String id;
  private String name;
  private Long stock;
  private Double price;

  public static BookDomain fromBook(Book book) {
    if (book != null) {
      return BookDomain.builder()
          .id(book.getId())
          .stock(book.getStock())
          .name(book.getName())
          .price(book.getPrice())
          .build();
    }
    return null;
  }
}
