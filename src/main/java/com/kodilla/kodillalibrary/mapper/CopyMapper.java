package com.kodilla.kodillalibrary.mapper;

import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.dto.CopyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CopyMapper {

    public Copy mapToCopy(final CopyDto copyDto, final Title title) {
        return new Copy(
                copyDto.getId(),
                title,
                copyDto.getStatus()
        );
    }

    public CopyDto mapToCopyDto(final Copy copy) {
        return new CopyDto(
                copy.getId(),
                copy.getTitle().getId(),
                copy.getStatus()
        );
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copyList) {
        return copyList.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }
}
