package com.satriaaldian.rest.dtos.nasabah;

import com.satriaaldian.rest.models.Nasabah;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class NasabahHeaderDto implements Serializable {
    private final Integer id;
    private final String namaDepan;
    private final String namaBelakang;
    private final String tempatLahir;
    private final LocalDate tanggalLahir;
    private final String jenisKelamin;
    private final String alamat;
    private final String noHandphone;

    public static NasabahHeaderDto set(Nasabah nasabah){
        return new NasabahHeaderDto(
                nasabah.getId(),
                nasabah.getNamaDepan(),
                nasabah.getNamaBelakang(),
                nasabah.getTempatLahir(),
                nasabah.getTanggalLahir(),
                nasabah.getJenisKelamin(),
                nasabah.getAlamat(),
                nasabah.getNoHandphone());
    }

    public static List<NasabahHeaderDto> toList(List<Nasabah> nasabahs){
        List<NasabahHeaderDto> result = new ArrayList<>();
        for (Nasabah nasabah: nasabahs
             ) { result.add(set(nasabah));
        } return result;
    }
}
