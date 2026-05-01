package com.kodilla.kodillalibrary.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "borrow_date", updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private LocalDateTime borrowDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;
}
