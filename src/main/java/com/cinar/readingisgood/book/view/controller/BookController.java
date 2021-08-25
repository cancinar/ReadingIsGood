package com.cinar.readingisgood.book.view.controller;

import com.cinar.readingisgood.book.core.BookComponent;
import com.cinar.readingisgood.book.core.domain.BookDomain;
import com.cinar.readingisgood.book.view.dto.AddBookDto;
import com.cinar.readingisgood.book.view.dto.BookDto;
import com.cinar.readingisgood.book.view.dto.UpdateBookStockDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {

  private final BookComponent bookComponent;

  @PostMapping
  public ResponseEntity<BookDto> addBook(
      @RequestBody AddBookDto addCustomerDto) {
    final BookDomain bookDomain = bookComponent
        .addBook(addCustomerDto.getName(), addCustomerDto.getStock(), addCustomerDto.getPrice());

    return ResponseEntity.ok(BookDto.fromBookDomain(bookDomain));
  }


  @PutMapping("/{bookName}")
  public ResponseEntity<BookDto> updateBookStock(
      @PathVariable("bookName") String bookName, @RequestParam Long stock) {
    final BookDomain bookDomain = bookComponent.updateStock
        (bookName, stock);

    return ResponseEntity.ok(BookDto.fromBookDomain(bookDomain));
  }

}
