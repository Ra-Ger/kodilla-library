package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.dto.RentalDto;
import com.kodilla.kodillalibrary.mapper.RentalMapper;
import com.kodilla.kodillalibrary.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/rentals")
public class RentalController {

    private final RentalMapper mapper;

    private final LibraryService libraryService;

    @GetMapping
    public List<RentalDto> getRentals() {
        return mapper.mapToRentalDtoList(libraryService.getAllRentals());
    }

    @PostMapping("/rent")
    public ResponseEntity<Void> rentBook(@RequestParam Long copyId, @RequestParam Long readerId) {
        libraryService.rentBook(copyId, readerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/return")
    public ResponseEntity<Void> returnBook(@RequestParam Long rentalId) {
        libraryService.returnBook(rentalId);
        return ResponseEntity.ok().build();
    }
}