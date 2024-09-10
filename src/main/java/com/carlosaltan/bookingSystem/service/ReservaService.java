package com.carlosaltan.bookingSystem.service;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.carlosaltan.bookingSystem.model.Reserva;
import org.springframework.stereotype.Service;

@Service
public class ReservaService implements IReservaService {
    private final Map<Long, Reserva> reservas = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public Reserva createReserva(Reserva reserva) {
        reserva.setId(currentId++);
        reservas.put(reserva.getId(), reserva);
        return reserva;
    }

    @Override
    public Optional<Reserva> searchReserva(Long id) {
        return Optional.ofNullable(reservas.get(id));
    }

    @Override
    public Reserva updateReserva(Long id, Reserva reserva) {
        if (reservas.containsKey(id)) {
            reserva.setId(id);
            reservas.put(id, reserva);
            return reserva;
        }
        return null;
    }

    @Override
    public void deleteReserva(Long id) {
        reservas.remove(id);
    }

    @Override
    public List<Reserva> getAllReservas() {
        return new ArrayList<>(reservas.values());
    }

}
