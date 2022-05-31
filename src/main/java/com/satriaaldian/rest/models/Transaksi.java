package com.satriaaldian.rest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaksi", nullable = false)
    private Integer id;

    @Column(name = "no_transaksi",nullable = false)
    private Integer noTransaksi;

    @Column(name = "jenis_transaksi" ,nullable = false,length = 10)
    private String jenisTransaksi;

    @Column(name = "tanggal_transaksi",nullable = false)
    private LocalDate tanggalTransaksi;

    @Column(name = "jumlah_transaksi",nullable = false)
    private Integer jumlahTransaksi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "no_rekening", nullable = false)
    private Rekening noRekening;

    public Transaksi(Integer noTransaksi, String jenisTransaksi, LocalDate tanggalTransaksi, Integer jumlahTransaksi) {
        this.noTransaksi = noTransaksi;
        this.jenisTransaksi = jenisTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.jumlahTransaksi = jumlahTransaksi;
    }


    public Transaksi(Rekening rekening,Transaksi insertTransaksi) {
        this.noRekening = rekening;
        this.noTransaksi = insertTransaksi.getNoTransaksi();
        this.jenisTransaksi = insertTransaksi.getJenisTransaksi();
        this.tanggalTransaksi = insertTransaksi.getTanggalTransaksi();
        this.jumlahTransaksi = insertTransaksi.getJumlahTransaksi();
    }

}