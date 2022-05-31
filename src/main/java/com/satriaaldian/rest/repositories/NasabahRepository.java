package com.satriaaldian.rest.repositories;

import com.satriaaldian.rest.models.Nasabah;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NasabahRepository extends JpaRepository<Nasabah, Integer> {
}