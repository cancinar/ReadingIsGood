package com.cinar.readingisgood.auth.view.dto;

import java.io.Serializable;

public class AuthResponseDto implements Serializable {

  private static final long serialVersionUID = -8091879091924046844L;
  private final String jwttoken;

  public AuthResponseDto(String jwttoken) {
    this.jwttoken = jwttoken;
  }

  public String getToken() {
    return this.jwttoken;
  }
}