package com.kodilla.kodillalibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.kodillalibrary.assets.CopyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopyDto {
    private Long id;
    @JsonProperty("title_id")
    private long titleId;
    private CopyStatus status;
}
