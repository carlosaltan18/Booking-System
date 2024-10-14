package com.carlosaltan.bookingSystem.service;
import com.carlosaltan.bookingSystem.model.Reserva;
import java.util.List;
import java.util.Optional;
public interface IReservaService {
    Reserva createReserva(Reserva reserva);
    Optional<Reserva> searchReserva(String id);
    Reserva updateReserva(String id, Reserva reserva);
    void deleteReserva(String id);
    List<Reserva> getAllReservas();

}
