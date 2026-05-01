package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    @Override
    List<Reader> findAll();
}