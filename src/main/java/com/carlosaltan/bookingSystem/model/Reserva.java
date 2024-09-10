package com.carlosaltan.bookingSystem.model;
import java.time.LocalDate;
import java.util.Date;

public class Reserva {

    private Long id;
    private Long usuarioId;
    private LocalDate fechaReserva;
    private Date fechaEntrega;
    private Boolean estado;

    public Reserva() {
    }

    public Reserva(Boolean estado, Date fechaEntrega, LocalDate fechaReserva, Long id, Long usuarioId) {
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.fechaReserva = fechaReserva;
        this.id = id;
        this.usuarioId = usuarioId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
