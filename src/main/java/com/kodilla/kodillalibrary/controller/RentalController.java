package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Rental;
import com.kodilla.kodillalibrary.dto.RentalDto;
import com.kodilla.kodillalibrary.mapper.RentalMapper;
import com.kodilla.kodillalibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rentals")
public class RentalController {

    @Autowired
    private RentalMapper mapper;

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public List<RentalDto> getRentals() {
        return mapper.mapToRentalDtoList(libraryService.getAllRentals());
    }

    @PostMapping("/rent")
    public ResponseEntity<Void> rentBook(@RequestParam Long copyId, @RequestParam Long readerId) throws Exception {
        libraryService.rentBook(copyId, readerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestParam Long rentalId) throws Exception {
        libraryService.returnBook(rentalId);
        return ResponseEntity.ok().build();
    }
}