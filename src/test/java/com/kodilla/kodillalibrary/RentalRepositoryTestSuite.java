package com.kodilla.kodillalibrary;

import com.kodilla.kodillalibrary.assets.CopyStatus;
import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.domain.Rental;
import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.repository.CopyRepository;
import com.kodilla.kodillalibrary.repository.ReaderRepository;
import com.kodilla.kodillalibrary.repository.RentalRepository;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RentalRepositoryTestSuite {

    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private RentalRepository rentalRepository;

    @Transactional
    @Test
    void rentalSaveTestt() {
        //Given
        Title title = new Title(null, "Wsioker", "Andrzej Jabolski", 1992);
        Title savedTitle = titleRepository.save(title);
        Copy copy = new Copy(null, savedTitle, CopyStatus.RENTED);
        Copy savedCopy = copyRepository.save(copy);
        Reader reader = new Reader(null, "Grzegorz", "Brzenczyszczaw", null);
        Reader savedReader = readerRepository.save(reader);
        Rental rental = new Rental(null, savedCopy, savedReader, LocalDateTime.of(2024, 9, 3, 10, 55), LocalDateTime.of(2024, 9, 3, 10, 55));

        //When
        rentalRepository.save(rental);
        long id = rental.getId();
        Optional<Rental> readRental = rentalRepository.findById(id);

        //Then
        assertTrue(readRental.isPresent());
        assertEquals("Wsioker", readRental.get().getCopy().getTitle().getTitle());

        //CleanUp
        rentalRepository.deleteById(id);
        readerRepository.deleteById(savedReader.getId());
        copyRepository.deleteById(savedCopy.getId());
        titleRepository.deleteById(savedTitle.getId());
    }
}
