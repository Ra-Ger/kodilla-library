package com.kodilla.kodillalibrary;

import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TitleRepositoryTestSuite {

    @Autowired
    private TitleRepository titleRepository;

    @Transactional
    @Test
    void testTitleSave() {
        //Given
        Title title = new Title(null, "Wsioker", "Andrzej Jabolski", 1992);

        //When
        titleRepository.save(title);
        Long id = title.getId();
        Optional<Title> readTitle = titleRepository.findById(id);

        //Then
        assertTrue(readTitle.isPresent());
        assertEquals("Wsioker", readTitle.get().getTitle());

        //CleanUp
        titleRepository.deleteById(id);
    }
}
