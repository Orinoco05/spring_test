package com.example.test.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lop")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lop {
    @Id
    private Long id;

    private String name;
    }

