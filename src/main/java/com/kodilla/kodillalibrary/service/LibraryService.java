package com.kodilla.kodillalibrary.service;

import com.kodilla.kodillalibrary.assets.CopyStatus;
import com.kodilla.kodillalibrary.domain.*;
import com.kodilla.kodillalibrary.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final ReaderRepository readerRepository;
    private final TitleRepository titleRepository;
    private final CopyRepository copyRepository;
    private final RentalRepository rentalRepository;

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader getReaderById(final Long id) throws Exception{
        return readerRepository.findById(id).orElseThrow(() -> new Exception("Reader not found"));
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public Title getTitleById(final Long id) throws Exception {
        return titleRepository.findById(id).orElseThrow(() -> new Exception("Title not found"));
    }
    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Copy saveCopy(final Copy copy) {
        return copyRepository.save(copy);
    }
    public Copy getCopyById(final Long id) throws Exception {
        return copyRepository.findById(id).orElseThrow(() -> new Exception("Copy didn't exist."));
    }
    public List<Copy> getAllCopies() {
        return copyRepository.findAll();
    }
    public Rental saveRental(final Rental rental) {
        return rentalRepository.save(rental);
    }
    public Rental getRentalById(final Long id) throws Exception {
        return rentalRepository.findById(id).orElseThrow(() -> new Exception("Rental didn't exist."));
    }
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Copy updateCopyStatus(final long id, final CopyStatus copyStatus) throws Exception {
        Copy copy = copyRepository.findById(id).orElseThrow(() -> new Exception("Copy didn't exist."));
        Copy updatedCopy = new Copy(copy.getId(), copy.getTitle(),copyStatus);
        return copyRepository.save(updatedCopy);
    }

    public long availableCopiesCount(final long titleId) {
        return copyRepository.countByTitleIdAndStatus(titleId, CopyStatus.AVAILABLE);
    }

    public Rental rentBook(final long copyId, final long readerId) throws Exception {
        Copy copyToRent = copyRepository.findById(copyId).orElseThrow(() -> new Exception("Copy didn't exist."));
        if(!copyToRent.getStatus().equals(CopyStatus.AVAILABLE))
        { throw new Exception("Book is not available."); }
        Reader reader = readerRepository.findById(readerId).orElseThrow(() -> new Exception("Reader didn't exist."));
        updateCopyStatus(copyToRent.getId(), CopyStatus.RENTED);

        return rentalRepository.save(new Rental(null,copyToRent,reader,LocalDateTime.now(),null));
    }

    public Rental returnBook(final long rentalId) throws Exception {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(() -> new Exception("Rental didn't exist."));
        updateCopyStatus(rental.getCopy().getId(), CopyStatus.AVAILABLE);
        return rentalRepository.save(new Rental(rental.getId(),rental.getCopy(),rental.getReader(),rental.getBorrowDate(),LocalDateTime.now()));
    }
}
