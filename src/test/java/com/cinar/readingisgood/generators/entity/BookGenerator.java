package com.cinar.readingisgood.generators.entity;

import static com.cinar.readingisgood.generators.RandomGenerator.randomBookName;
import static com.cinar.readingisgood.generators.RandomGenerator.randomDouble;
import static com.cinar.readingisgood.generators.RandomGenerator.randomId;
import static com.cinar.readingisgood.generators.RandomGenerator.randomLong;

import com.cinar.readingisgood.book.core.entity.Book;

public class BookGenerator {

  public static Book randomBook() {
    return Book.builder()
        .id(randomId())
        .name(randomBookName())
        .stock(randomLong())
        .price(randomDouble())
        .build();
  }
}
