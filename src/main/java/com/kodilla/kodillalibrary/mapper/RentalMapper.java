package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.domain.Reader;
import com.kodilla.kodillalibrary.domain.Rental;
import com.kodilla.kodillalibrary.dto.RentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RentalMapper {
    public Rental mapToRental(final RentalDto rentalDto, final Copy copy, final Reader reader) {
        return new Rental(
                rentalDto.getId(),
                copy,
                reader,
                rentalDto.getBorrowDate(),
                rentalDto.getReturnDate()
        );
    }

    public RentalDto mapToRentalDto(final Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getCopy().getId(),
                rental.getReader().getId(),
                rental.getBorrowDate(),
                rental.getReturnDate()
        );
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }
}
