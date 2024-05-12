package com.example.test.repository;

import com.example.test.domain.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LopRepository extends JpaRepository<Lop,Long> {
}
