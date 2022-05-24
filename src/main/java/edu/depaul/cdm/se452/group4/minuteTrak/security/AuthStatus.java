package edu.depaul.cdm.se452.group4.minuteTrak.security;

import lombok.Data;

@Data
public class AuthStatus {

  private int id;

  private String role;

  public AuthStatus(String id, String role) {
    this.id = Integer.parseInt(id);
    this.role = role;
  }

  public AuthStatus(int id, String role) {
    this.id = id;
    this.role = role;
  }

}
