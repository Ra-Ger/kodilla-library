package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.dto.TitleDto;
import com.kodilla.kodillalibrary.mapper.TitleMapper;
import com.kodilla.kodillalibrary.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/titles")
public class TitleController {

    private final TitleMapper mapper;

    private final LibraryService libraryService;

    @GetMapping(value = "/{titleId}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable("titleId") Long titleId) {
       return ResponseEntity.ok(mapper.mapToTitleDto(libraryService.getTitleById(titleId)));
    }

    @GetMapping
    public List<TitleDto> getTitles() {
        return mapper.mapToTitleDtoList(libraryService.getAllTitles());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TitleDto> addTitle(@RequestBody TitleDto titleDto) {
        Title title = libraryService.saveTitle(mapper.mapToTitle(titleDto));
        return ResponseEntity.status(201).body(mapper.mapToTitleDto(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TitleDto> updateTitle(@PathVariable Long id, @RequestBody TitleDto titleDto) {
        Title title = mapper.mapToTitle(new TitleDto(id,titleDto.getTitle(),titleDto.getAuthor(), titleDto.getPublicationYear()));
        Title savedTitle = libraryService.saveTitle(title);
        return ResponseEntity.ok(mapper.mapToTitleDto(savedTitle));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> returnCopiesCount(@RequestParam Long titleId) {
        long count = libraryService.availableCopiesCount(titleId);
        return ResponseEntity.ok(count);
    }
}