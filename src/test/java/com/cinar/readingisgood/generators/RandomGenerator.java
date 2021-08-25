package com.cinar.readingisgood.generators;

import com.github.javafaker.Faker;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomGenerator {

  private final static Faker faker = new Faker();

  public static String randomId() {
    return faker.lorem().characters(5, 10, true);
  }

  public static String randomName() {
    return faker.name().firstName();
  }

  public static String randomBookName() {
    return faker.book().title();
  }

  public static String randomSurname() {
    return faker.name().lastName();
  }

  public static String randomEmail() {
    return faker.internet().emailAddress();
  }

  public static String randomPassword() {
    return faker.internet().password();
  }

  public static int randomInt() {
    return faker.number().randomDigitNotZero();
  }

  public static long randomLong() {
    return faker.number().randomNumber();
  }

  public static double randomDouble() {
    return faker.number().randomDouble(2, 0L, 1000000L);
  }

  public static <E extends Enum<E>> E randomEnum(Class<E> enumeration) {
    return faker.options().option(enumeration);
  }

  public static <T> List<T> randomList(Supplier<T> randomGeneratorFunc, int size) {
    return IntStream.rangeClosed(1, size)
        .boxed()
        .map(i -> randomGeneratorFunc.get())
        .collect(Collectors.toList());
  }

  public static <T> List<T> randomList(Supplier<T> randomGeneratorFunc) {
    return randomList(randomGeneratorFunc, randomInt() + 1);
  }

}
