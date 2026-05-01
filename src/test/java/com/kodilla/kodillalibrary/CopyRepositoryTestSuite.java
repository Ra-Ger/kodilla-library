package com.kodilla.kodillalibrary;

import com.kodilla.kodillalibrary.assets.CopyStatus;
import com.kodilla.kodillalibrary.domain.Copy;
import com.kodilla.kodillalibrary.domain.Title;
import com.kodilla.kodillalibrary.repository.CopyRepository;
import com.kodilla.kodillalibrary.repository.TitleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CopyRepositoryTestSuite {
    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private TitleRepository titleRepository;

    @Transactional
    @Test
    void testCopySave() {
        // Given
        Title title = new Title(null, "Wsioker", "Andrzej Jabolski", 1992);
        Title savedTitle = titleRepository.save(title);

        Copy copy = new Copy(null, savedTitle, CopyStatus.AVAILABLE);

        // When
        Copy savedCopy = copyRepository.save(copy);
        Long copyId = savedCopy.getId();
        Optional<Copy> readCopy = copyRepository.findById(copyId);

        // Then
        assertTrue(readCopy.isPresent());
        assertEquals(CopyStatus.AVAILABLE, readCopy.get().getStatus());

        copyRepository.deleteById(copyId);
        titleRepository.deleteById(savedTitle.getId());
    }
}

