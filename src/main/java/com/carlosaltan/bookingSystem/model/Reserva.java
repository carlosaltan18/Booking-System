package com.carlosaltan.bookingSystem.model;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "reserva")
public class Reserva {

    @Id
    private String id;
    private Usuario usuarioId;
    private LocalDate fechaReserva;
    private Date fechaEntrega;
    private Boolean estado;


}
