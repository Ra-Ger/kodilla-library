package com.kodilla.kodillalibrary;

import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReaderRepositoryTestSuite {


    @Autowired
    private ReaderRepository readerRepository;

    @Transactional
    @Test
    void readerSaveTest() {
        //Given
        Reader reader = new Reader(null, "Grzegorz", "Brzenczyszczaw", null);

        //When
        readerRepository.save(reader);
        long id = reader.getId();
        Optional<Reader> readReader = readerRepository.findById(id);
        ChronoLocalDate registrationTime = ChronoLocalDate.from(readReader.get().getRegistrationDate());

        //Then
        assertTrue(readReader.isPresent());
        assertEquals("Grzegorz", readReader.get().getFirstname());
        assertTrue(readReader.get().getRegistrationDate().toLocalDate().isEqual(registrationTime));

        //CleanUp
        readerRepository.deleteById(id);
    }
}
