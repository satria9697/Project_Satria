package com.satriaaldian.rest.dtos.transaksi;

import com.satriaaldian.rest.dtos.rekening.RekeningInsertDto;
import com.satriaaldian.rest.models.Transaksi;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TransaksiInsertDto implements Serializable {
    private Long noTransaksi;
    private String jenisTransaksi;
    private LocalDate tanggalTransaksi;
    private Integer jumlahTransaksi;


}
