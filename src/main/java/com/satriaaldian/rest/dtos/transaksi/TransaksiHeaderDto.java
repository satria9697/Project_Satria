package com.satriaaldian.rest.dtos.transaksi;

import com.satriaaldian.rest.models.Transaksi;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TransaksiHeaderDto implements Serializable {
    private final Integer id;
    private final Long noTransaksi;
    private final String jenisTransaksi;
    private final LocalDateTime tanggalTransaksi;
    private final Integer jumlahTransaksi;

    public static TransaksiHeaderDto set(Transaksi transaksi){
        return new TransaksiHeaderDto(transaksi.getId(),
                transaksi.getNoTransaksi(),
                transaksi.getJenisTransaksi(),
                transaksi.getTanggalTransaksi(),
                transaksi.getJumlahTransaksi());
    }


    public static List<TransaksiHeaderDto> toList(List<Transaksi> transaksis){
        List<TransaksiHeaderDto> result = new ArrayList<>();
        for (Transaksi transaksi: transaksis
             ) { result.add(set(transaksi));
        } return result;
    }
}
