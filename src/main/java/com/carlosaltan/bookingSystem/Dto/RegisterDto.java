package com.carlosaltan.bookingSystem.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String surname;
    private String dpi;
    private String email;
    private String password;
}
