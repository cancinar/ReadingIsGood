package com.cinar.readingisgood.book.view.dto;

import com.cinar.readingisgood.book.core.domain.BookDomain;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {

  private String id;
  private String name;

  public static BookDto fromBookDomain(BookDomain bookDomain) {
    return BookDto.builder()
        .id(bookDomain.getId())
        .name(bookDomain.getName())
        .build();
  }
}
