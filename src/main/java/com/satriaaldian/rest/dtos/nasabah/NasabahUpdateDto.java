package com.satriaaldian.rest.dtos.nasabah;

import lombok.Data;

import java.io.Serializable;

@Data
public class NasabahUpdateDto implements Serializable {

    private final String namaDepan;
    private final String namaBelakang;
    private final String tempatLahir;
    private final String tanggalLahir;
    private final String jenisKelamin;
    private final String alamat;
    private final String noHandphone;

}
