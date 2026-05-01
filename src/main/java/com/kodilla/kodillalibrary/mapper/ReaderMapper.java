package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.dto.ReaderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReaderMapper {
    
    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getFirstname(),
                readerDto.getLastname(),
                readerDto.getRegistrationDate()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstname(),
                reader.getLastname(),
                reader.getRegistrationDate()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }
}
