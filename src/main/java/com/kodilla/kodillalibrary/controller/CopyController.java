package com.kodilla.kodillalibrary.controller;

import com.kodilla.kodillalibrary.assets.CopyStatus;
import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.dto.CopyDto;
import com.kodilla.kodillalibrary.mapper.CopyMapper;
import com.kodilla.kodillalibrary.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/copies")
public class CopyController {

    private final CopyMapper mapper;

    private final LibraryService libraryService;

    @GetMapping
    public List<CopyDto> getCopies() {
        return mapper.mapToCopyDtoList(libraryService.getAllCopies());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CopyDto> addCopy(@RequestBody CopyDto copyDto) {
        Title title = libraryService.getTitleById(copyDto.getTitleId());
        Copy copy = mapper.mapToCopy(copyDto, title);
        Copy saved = libraryService.saveCopy(copy);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.mapToCopyDto(saved));
    }

    @PostMapping("status")
    public ResponseEntity<List<CopyDto>> updateCopyStatus(@RequestParam long copyId, @RequestParam CopyStatus copyStatus) {
        libraryService.updateCopyStatus(copyId,copyStatus);
        return ResponseEntity.ok().build();
    }
}
