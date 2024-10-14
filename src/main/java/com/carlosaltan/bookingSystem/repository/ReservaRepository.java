package com.carlosaltan.bookingSystem.repository;

import com.carlosaltan.bookingSystem.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
}
