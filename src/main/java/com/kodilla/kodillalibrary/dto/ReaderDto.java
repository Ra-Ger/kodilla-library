package com.kodilla.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDateTime registrationDate;
}
