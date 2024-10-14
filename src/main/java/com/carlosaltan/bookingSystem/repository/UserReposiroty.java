package com.carlosaltan.bookingSystem.repository;

import com.carlosaltan.bookingSystem.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserReposiroty extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByName(String name);
}
