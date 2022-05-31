package com.satriaaldian.rest.repositories;

import com.satriaaldian.rest.models.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RekeningRepository extends JpaRepository<Rekening, Integer> {
}