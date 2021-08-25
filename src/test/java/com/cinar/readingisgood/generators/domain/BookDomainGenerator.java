package com.cinar.readingisgood.generators.domain;

import static com.cinar.readingisgood.generators.RandomGenerator.randomBookName;
import static com.cinar.readingisgood.generators.RandomGenerator.randomDouble;
import static com.cinar.readingisgood.generators.RandomGenerator.randomId;
import static com.cinar.readingisgood.generators.RandomGenerator.randomLong;

import com.cinar.readingisgood.book.core.domain.BookDomain;

public class BookDomainGenerator {

  public static BookDomain randomBookDomain() {
    return BookDomain.builder()
        .name(randomBookName())
        .price(randomDouble())
        .stock(randomLong())
        .id(randomId())
        .build();
  }
}
