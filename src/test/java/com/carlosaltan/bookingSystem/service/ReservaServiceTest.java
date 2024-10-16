package com.carlosaltan.bookingSystem.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.carlosaltan.bookingSystem.excepcion.ResourceNotFoundException;
import com.carlosaltan.bookingSystem.model.Reserva;
import com.carlosaltan.bookingSystem.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateReserva() {
        Reserva reserva = new Reserva();
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        Reserva createdReserva = reservaService.createReserva(reserva);

        assertNotNull(createdReserva);
        verify(reservaRepository, times(1)).save(reserva);
    }


    @Test
    void testSearchReserva() {
        String reservaId = "123";
        Reserva reserva = new Reserva();
        reserva.setId(reservaId);
        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

        Optional<Reserva> foundReserva = reservaService.searchReserva(reservaId);

        assertTrue(foundReserva.isPresent());
        assertEquals(reservaId, foundReserva.get().getId());
        verify(reservaRepository, times(1)).findById(reservaId);
    }

    @Test
    void testUpdateReserva() {
        String reservaId = "123";
        Reserva reserva = new Reserva();
        reserva.setId(reservaId);

        when(reservaRepository.existsById(reservaId)).thenReturn(true);
        when(reservaRepository.save(reserva)).thenReturn(reserva);

        Reserva updatedReserva = reservaService.updateReserva(reservaId, reserva);

        assertEquals(reservaId, updatedReserva.getId());
        verify(reservaRepository, times(1)).existsById(reservaId);
        verify(reservaRepository, times(1)).save(reserva);
    }


    @Test
    void testUpdateReserva_NotFound() {
        String reservaId = "123";
        Reserva reserva = new Reserva();
        when(reservaRepository.existsById(reservaId)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            reservaService.updateReserva(reservaId, reserva);
        });

        assertEquals("Reserva no encontrada con ID: " + reservaId, exception.getMessage());
        verify(reservaRepository, times(1)).existsById(reservaId);
        verify(reservaRepository, never()).save(reserva);
    }


    @Test
    void testDeleteReserva() {
        String reservaId = "123";
        when(reservaRepository.existsById(reservaId)).thenReturn(true);

        reservaService.deleteReserva(reservaId);

        verify(reservaRepository, times(1)).existsById(reservaId);
        verify(reservaRepository, times(1)).deleteById(reservaId);
    }


    @Test
    void testDeleteReserva_NotFound() {
        String reservaId = "123";
        when(reservaRepository.existsById(reservaId)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            reservaService.deleteReserva(reservaId);
        });

        assertEquals("Reserva no encontrada con ID: " + reservaId, exception.getMessage());
        verify(reservaRepository, times(1)).existsById(reservaId);
        verify(reservaRepository, never()).deleteById(reservaId);
    }

    @Test
    void testGetAllReservas() {
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(new Reserva());
        when(reservaRepository.findAll()).thenReturn(reservas);

        List<Reserva> allReservas = reservaService.getAllReservas();

        assertEquals(1, allReservas.size());
        verify(reservaRepository, times(1)).findAll();
    }
}