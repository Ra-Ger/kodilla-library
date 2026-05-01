package com.kodilla.kodillalibrary.repository;

import com.kodilla.kodillalibrary.assets.CopyStatus;
import com.kodilla.kodillalibrary.domain.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {
    @Override
    List<Copy> findAll();
    long countByTitleIdAndStatus(Long titleId, CopyStatus status);
}