package com.carlosaltan.bookingSystem.controller;


import com.carlosaltan.bookingSystem.Dto.ReservaDto;
import com.carlosaltan.bookingSystem.model.Reserva;
import com.carlosaltan.bookingSystem.model.Usuario;
import com.carlosaltan.bookingSystem.repository.UserReposiroty;
import com.carlosaltan.bookingSystem.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private UserReposiroty userReposiroty;

    @PostMapping
    public ResponseEntity<Reserva>  createReserva(@RequestBody ReservaDto dto) {
        Optional<Usuario> usuarioOpt = userReposiroty.findByName(dto.getNameUser());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario usuario = usuarioOpt.get(); // Obtener el usuario
        Reserva reserva = new Reserva();
        reserva.setUsuarioId(usuario);
        reserva.setFechaReserva(dto.getFechaReserva());
        reserva.setFechaEntrega(dto.getFechaEntrega());
        reserva.setEstado(dto.getEstado());

        // Crear la reserva
        Reserva nuevaReserva = reservaService.createReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva); // Devuelve un 201 con la reserva creada
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable String id) {
        Optional<Reserva> reservaOpt = reservaService.searchReserva(id);
        return reservaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable String id, @RequestBody Reserva reserva) {
        Reserva updatedReserva = reservaService.updateReserva(id, reserva);
        return ResponseEntity.ok(updatedReserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable String id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content
    }

    @GetMapping
    public List<Reserva> getAllReservas() {
        return reservaService.getAllReservas();
    }
}
