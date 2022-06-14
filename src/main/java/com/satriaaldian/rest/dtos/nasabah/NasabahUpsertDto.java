package com.satriaaldian.rest.dtos.nasabah;

import com.satriaaldian.rest.models.Nasabah;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class NasabahUpsertDto implements Serializable {

    private final String namaDepan;
    private final String namaBelakang;
    private final String tempatLahir;
    private final String tanggalLahir;
    private final String jenisKelamin;
    private final String alamat;
    private final String noHandphone;

    public Nasabah insertNasabah(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Nasabah(
                namaDepan,
                namaBelakang,
                tempatLahir,
                LocalDate.parse(tanggalLahir,date),
                jenisKelamin,
                alamat,
                noHandphone);
    }
}
