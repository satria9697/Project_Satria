package com.satriaaldian.rest.dtos.rekening;

import com.satriaaldian.rest.models.Rekening;
import lombok.Data;

import java.io.Serializable;

@Data
public class RekeningInsertDto implements Serializable {
    private final Integer no_rekening;
    private final Integer saldo = 0;

//    public Rekening insertRekeningNasabah(){
//        return new Rekening(no_rekening,saldo);
//    }
}
