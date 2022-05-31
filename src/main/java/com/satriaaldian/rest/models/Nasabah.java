package com.satriaaldian.rest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Nasabah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nasabah", nullable = false)
    private Integer id;

    @Column(name = "NamaDepan", nullable = false, length = 20)
    private String namaDepan;

    @Column(name = "NamaBelakang", length = 20)
    private String namaBelakang;

    @Column(name = "TempatLahir", nullable = false, length = 20)
    private String tempatLahir;

    @Column(name = "TanggalLahir", nullable = false)
    private LocalDate tanggalLahir;

    @Column(name = "JenisKelamin", nullable = false, length = 10)
    private String jenisKelamin;

    @Column(name = "Alamat", nullable = false)
    private String alamat;

    @Column(name = "NoHandphone", length = 20)
    private String noHandphone;

    @OneToMany(mappedBy = "idNasabah")
    private Set<Rekening> rekenings = new LinkedHashSet<>();


    public Nasabah(String namaDepan, String namaBelakang, String tempatLahir, LocalDate tanggalLahir, String jenisKelamin, String alamat, String noHandphone) {
        this.namaDepan = namaDepan;
        this.namaBelakang = namaBelakang;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.noHandphone = noHandphone;
    }
}