package com.carlosaltan.bookingSystem.service;
import com.carlosaltan.bookingSystem.model.Reserva;
import java.util.List;
import java.util.Optional;
public interface IReservaService {
    Reserva createReserva(Reserva reserva);
    Optional<Reserva> searchReserva(Long id);
    Reserva updateReserva(Long id, Reserva reserva);
    void deleteReserva(Long id);
    List<Reserva> getAllReservas();

}
