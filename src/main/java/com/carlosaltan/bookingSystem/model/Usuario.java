package com.carlosaltan.bookingSystem.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "usuario")
public class Usuario {
    @Id
    private String id;
    private String name;
    private String surname;
    private String dpi;
    private String email;
    private String password;


}
