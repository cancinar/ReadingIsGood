package com.cinar.readingisgood.customer.core;

import static com.cinar.readingisgood.generators.RandomGenerator.randomEmail;
import static com.cinar.readingisgood.generators.RandomGenerator.randomName;
import static com.cinar.readingisgood.generators.RandomGenerator.randomPassword;
import static com.cinar.readingisgood.generators.RandomGenerator.randomSurname;
import static com.cinar.readingisgood.generators.entity.CustomerGenerator.randomCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.cinar.readingisgood.base.exception.ValidationException;
import com.cinar.readingisgood.customer.core.domain.CustomerDomain;
import com.cinar.readingisgood.customer.core.entity.Customer;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerComponentImplTest {

  private CustomerComponent customerComponent;

  @Mock
  private CustomerJPARepository customerJPARepository;

  private static final String VALIDATION_EXCEPTION_MESSAGE = "Name, Surname, Password or Email cannot be empty.";

  @BeforeEach
  public void init() {
    customerComponent = new CustomerComponentImpl(customerJPARepository);
  }

  @Test
  public void addCustomer_whenNameIsNull_thenThrowValidationException() {
    Exception exception = assertThrows(ValidationException.class,
        () -> customerComponent
            .addCustomer(null, randomSurname(), randomEmail(), randomPassword()));

    assertEquals(VALIDATION_EXCEPTION_MESSAGE, exception.getMessage());
  }

  @Test
  public void addCustomer_whenSurnameIsNull_thenThrowValidationException() {
    Exception exception = assertThrows(ValidationException.class,
        () -> customerComponent.addCustomer(randomName(), null, randomEmail(), randomPassword()));

    assertEquals(VALIDATION_EXCEPTION_MESSAGE, exception.getMessage());
  }

  @Test
  public void addCustomer_whenEmailIsNull_thenThrowValidationException() {
    Exception exception = assertThrows(ValidationException.class,
        () -> customerComponent.addCustomer(randomName(), randomSurname(), null, randomPassword()));

    assertEquals(VALIDATION_EXCEPTION_MESSAGE, exception.getMessage());
  }

  @Test
  public void addCustomer_whenPasswordIsNull_thenThrowValidationException() {
    Exception exception = assertThrows(ValidationException.class,
        () -> customerComponent.addCustomer(randomName(), randomSurname(), randomEmail(), null));

    assertEquals(VALIDATION_EXCEPTION_MESSAGE, exception.getMessage());
  }

  @Test
  public void addCustomer_givenCustomer_whenCustomerAlreadyAdded_thenThrowValidationException() {
    Customer customer = randomCustomer();
    when(customerJPARepository.findByEmailIgnoreCase(customer.getEmail()))
        .thenReturn(Optional.of(customer));

    Exception exception = assertThrows(ValidationException.class,
        () -> customerComponent
            .addCustomer(customer.getName(), customer.getSurname(), customer.getEmail(),
                customer.getPassword()
            ));

    assertEquals(customer.getEmail() + " has already been added.", exception.getMessage());
  }

  @Test
  public void addCustomer_givenCustomer_thenCustomer() {
    Customer customer = randomCustomer();

    when(customerJPARepository.findByEmailIgnoreCase(customer.getEmail()))
        .thenReturn(Optional.empty());
    when(customerJPARepository.save(any(Customer.class))).thenAnswer(i -> i.getArgument(0));

    final CustomerDomain customerDomain = customerComponent
        .addCustomer(customer.getName(), customer.getSurname(), customer.getEmail(),
            customer.getPassword());

    final CustomerDomain expected = CustomerDomain.fromCustomer(customer);

    assertEquals(expected.getName(), customerDomain.getName());
    assertEquals(expected.getEmail(), customerDomain.getEmail());
    assertEquals(expected.getSurname(), customerDomain.getSurname());
  }

  @Test
  public void getCustomer_whenNoCustomer_thenThrowValidationException() {
    String email = randomEmail();

    when(customerJPARepository.findByEmailIgnoreCase(email))
        .thenReturn(Optional.empty());

    Exception exception = assertThrows(ValidationException.class,
        () -> customerComponent.getCustomer(email));

    assertEquals("Customer cannot be found!", exception.getMessage());
  }

  @Test
  public void getCustomer_givenCustomer_thenReturn() {
    Customer customer = randomCustomer();

    when(customerJPARepository.findByEmailIgnoreCase(customer.getEmail()))
        .thenReturn(Optional.of(customer));

    final CustomerDomain customerDomain = customerComponent.getCustomer(customer.getEmail());

    CustomerDomain expected = CustomerDomain.fromCustomer(customer);

    assertEquals(expected.getName(), customerDomain.getName());
    assertEquals(expected.getEmail(), customerDomain.getEmail());
    assertEquals(expected.getSurname(), customerDomain.getSurname());
  }
}
