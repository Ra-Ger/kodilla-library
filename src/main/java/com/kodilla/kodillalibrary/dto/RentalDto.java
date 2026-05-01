package com.kodilla.kodillalibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RentalDto {
    private Long id;
    @JsonProperty("copy_id")
    private Long copyId;
    @JsonProperty("reader_id")
    private Long readerId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}
