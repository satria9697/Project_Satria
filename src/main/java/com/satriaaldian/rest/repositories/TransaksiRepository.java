package com.satriaaldian.rest.repositories;

import com.satriaaldian.rest.models.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {

    @Query(value = """
            SELECT * 
            FROM Transaksi 
            WHERE tanggal_transaksi = :tanggalTransaksi
            """, nativeQuery = true)
    List<Transaksi> getTransaksiByDate(@Param("tanggalTransaksi") LocalDateTime tanggalTransaksi);


    @Query(value = """
            SELECT * 
            FROM Transaksi 
            WHERE no_rekening = :noRekening 
            """, nativeQuery = true)
    List<Transaksi> getTransaksiByNoRekening(@Param("noRekening")int noRekening);

}