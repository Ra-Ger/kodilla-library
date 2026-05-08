package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.dto.ReaderDto;
import com.kodilla.kodillalibrary.mapper.ReaderMapper;
import com.kodilla.kodillalibrary.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/readers")
public class ReaderController {

    private final ReaderMapper mapper;

    private final LibraryService libraryService;

    @GetMapping
    public List<ReaderDto> getReaders() {
        return mapper.mapToReaderDtoList(libraryService.getAllReaders());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReaderDto> addReader(@RequestBody ReaderDto readerDto) {
        Reader reader = libraryService.saveReader(mapper.mapToReader(readerDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToReaderDto(reader));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReaderDto> updateReader(@PathVariable Long id, @RequestBody ReaderDto readerDto) {
        Reader reader = mapper.mapToReader(new ReaderDto(id,readerDto.getFirstname(),readerDto.getLastname(), readerDto.getRegistrationDate()));
        Reader savedReader = libraryService.saveReader(reader);
        return ResponseEntity.ok(mapper.mapToReaderDto(savedReader));
    }
}

