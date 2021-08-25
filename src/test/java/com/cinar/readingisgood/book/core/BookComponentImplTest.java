package com.cinar.readingisgood.book.core;

import static com.cinar.readingisgood.generators.RandomGenerator.randomBookName;
import static com.cinar.readingisgood.generators.RandomGenerator.randomDouble;
import static com.cinar.readingisgood.generators.RandomGenerator.randomLong;
import static com.cinar.readingisgood.generators.RandomGenerator.randomName;
import static com.cinar.readingisgood.generators.entity.BookGenerator.randomBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.cinar.readingisgood.base.exception.ValidationException;
import com.cinar.readingisgood.book.core.domain.BookDomain;
import com.cinar.readingisgood.book.core.entity.Book;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookComponentImplTest {

  private BookComponentImpl bookComponent;

  @Mock
  private BookJPARepository bookJPARepository;

  @BeforeEach
  public void init() {
    bookComponent = new BookComponentImpl(bookJPARepository);
  }

  @Test
  public void addBook_whenNameIsNull_thenThrowValidationException() {
    Exception exception = assertThrows(ValidationException.class,
        () -> bookComponent.addBook(null, randomLong(), randomDouble()));

    assertEquals("Name or Stock cannot be empty.", exception.getMessage());

  }

  @Test
  public void addBook_whenStockIsNull_thenThrowValidationException() {
    Exception exception = assertThrows(ValidationException.class,
        () -> bookComponent.addBook(randomName(), null, randomDouble()));

    assertEquals("Name or Stock cannot be empty.", exception.getMessage());

  }

  @Test
  public void addBook_whenPriceIsZero_thenThrowValidationException() {
    Exception exception = assertThrows(ValidationException.class,
        () -> bookComponent.addBook(randomName(), randomLong(), 0D));

    assertEquals("Price should be more than zero.", exception.getMessage());
  }

  @Test
  public void addBook_givenBook_whenIsExist_thenThrowValidationException() {
    Book book = randomBook();

    when(bookJPARepository.findByNameIgnoreCase(book.getName())).thenReturn(Optional.of(book));

    Exception exception = assertThrows(ValidationException.class,
        () -> bookComponent.addBook(book.getName(), randomLong(), randomDouble()));
    assertEquals(book.getName() + " has already been added.", exception.getMessage());
  }

  @Test
  public void addBook_thenSuccess() {
    String bookName = randomBookName();
    double price = randomDouble();
    long stock = randomLong();

    when(bookJPARepository.findByNameIgnoreCase(bookName)).thenReturn(Optional.empty());

    when(bookJPARepository.save(any(Book.class))).thenAnswer((i) -> i.getArgument(0));
    final BookDomain bookDomain = bookComponent.addBook(bookName, stock, price);

    assertEquals(bookDomain.getName(), bookName);
    assertEquals(bookDomain.getPrice(), price);
    assertEquals(bookDomain.getStock(), stock);
  }

  @Test
  public void updateStock_whenIsNotExist_thenThrowValidationException() {
    String bookName = randomBookName();
    long stock = randomLong();

    when(bookJPARepository.findByNameIgnoreCase(bookName)).thenReturn(Optional.empty());

    Exception exception = assertThrows(ValidationException.class,
        () -> bookComponent.updateStock(bookName, stock));

    assertEquals("Book cannot be found!", exception.getMessage());
  }

  @Test
  public void updateStock_givenBook_whenIsExist_thenSuccess() {
    Book book = randomBook();
    Long newStock = randomLong();

    when(bookJPARepository.findByNameIgnoreCase(book.getName())).thenReturn(Optional.of(book));

    when(bookJPARepository.save(any(Book.class))).thenAnswer((i) -> i.getArgument(0));
    final BookDomain bookDomain = bookComponent.updateStock(book.getName(), newStock);

    assertEquals(bookDomain.getName(), book.getName());
    assertEquals(bookDomain.getStock(), newStock);
  }

  @Test
  public void getBook_givenBook() {
    Book book = randomBook();

    when(bookJPARepository.findByNameIgnoreCase(book.getName())).thenReturn(Optional.of(book));

    final BookDomain bookDomain = bookComponent.getBook(book.getName());

    assertEquals(bookDomain.getName(), book.getName());
    assertEquals(bookDomain.getStock(), book.getStock());
    assertEquals(bookDomain.getPrice(), book.getPrice());
  }
}
