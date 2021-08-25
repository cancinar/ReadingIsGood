package com.cinar.readingisgood.base.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntity {

  @CreatedBy
  private String createdBy;

  @LastModifiedBy
  private String updatedBy;

  @LastModifiedDate
  private LocalDate updatedDate;

  @CreatedDate
  private LocalDate createDate = LocalDate.now();

}
