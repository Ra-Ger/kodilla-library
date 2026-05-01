package com.kodilla.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private Integer publicationYear;
}
