package com.carlosaltan.bookingSystem.service;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.carlosaltan.bookingSystem.excepcion.ResourceNotFoundException;
import com.carlosaltan.bookingSystem.model.Reserva;
import com.carlosaltan.bookingSystem.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Optional<Reserva> searchReserva(String id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva updateReserva(String id, Reserva reserva) {
        if (reservaRepository.existsById(id)) {
            reserva.setId(id);
            return reservaRepository.save(reserva);
        }
        throw new ResourceNotFoundException("Reserva no encontrada con ID: " + id);
    }

    @Override
    public void deleteReserva(String id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Reserva no encontrada con ID: " + id);
        }
    }

    @Override
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }
}
