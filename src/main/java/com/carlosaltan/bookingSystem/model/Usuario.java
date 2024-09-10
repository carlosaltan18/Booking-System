package com.carlosaltan.bookingSystem.model;

public class Usuario {
    private Long id;
    private String name;
    private String surname;
    private String dpi;
    private String email;
    private String password;

    public Usuario() {
    }

    public Usuario(String dpi, String email, Long id, String name, String password, String surname) {
        this.dpi = dpi;
        this.email = email;
        this.id = id;
        this.name = name;
        this.password = password;
        this.surname = surname;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
