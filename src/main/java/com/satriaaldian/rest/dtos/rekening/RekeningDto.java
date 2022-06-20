package com.satriaaldian.rest.dtos.rekening;

import com.satriaaldian.rest.models.Nasabah;
import com.satriaaldian.rest.models.Rekening;
import lombok.Data;

import java.io.Serializable;

@Data
public class RekeningDto implements Serializable {
    private final Long no_Rekening;
    private final String nama_Lengkap;
    private final Integer saldo;

    public static RekeningDto set(Rekening rekening){
        return new RekeningDto(
                rekening.getId(),
                String.format("%s %s", rekening.getIdNasabah().getNamaDepan(), rekening.getIdNasabah().getNamaBelakang()),
                rekening.getSaldo());
    }
}
