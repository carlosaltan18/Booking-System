package com.carlosaltan.bookingSystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaDto {
    private String nameUser;
    private LocalDate fechaReserva;
    private Date fechaEntrega;
    private Boolean estado;
}
