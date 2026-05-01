package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
    @Override
    List<Rental> findAll();
}