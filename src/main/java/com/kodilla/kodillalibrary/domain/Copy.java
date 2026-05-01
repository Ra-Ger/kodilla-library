package com.kodilla.kodillalibrary.domain;

import com.kodilla.kodillalibrary.assets.CopyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "copies")
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CopyStatus status;
}


