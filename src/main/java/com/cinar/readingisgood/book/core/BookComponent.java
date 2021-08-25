package com.cinar.readingisgood.book.core;

import com.cinar.readingisgood.book.core.domain.BookDomain;

public interface BookComponent {

  BookDomain addBook(String name, Long stock, Double price);

  BookDomain updateStock(String name, Long stock);

  BookDomain getBook(String name);
}
