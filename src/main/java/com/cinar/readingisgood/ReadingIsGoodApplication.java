package com.cinar.readingisgood;

import com.cinar.readingisgood.base.exception.ValidationException;
import com.cinar.readingisgood.customer.core.CustomerComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ReadingIsGoodApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReadingIsGoodApplication.class, args);
  }


  @Bean
  public CommandLineRunner initDbData(CustomerComponent customerComponent) {
    return (arg) -> {
      try {
        customerComponent.getCustomer("unclebob@gmail.com");
      } catch (ValidationException ve) {
        customerComponent.addCustomer("Uncle", "Bob", "unclebob@gmail.com", "1234");
      }
    };
  }
}
