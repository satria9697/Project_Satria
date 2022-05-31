package com.satriaaldian.rest.services;

import com.satriaaldian.rest.dtos.transaksi.TransaksiHeaderDto;
import com.satriaaldian.rest.dtos.transaksi.TransaksiInsertDto;
import com.satriaaldian.rest.models.Rekening;
import com.satriaaldian.rest.models.Transaksi;
import com.satriaaldian.rest.repositories.RekeningRepository;
import com.satriaaldian.rest.repositories.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransaksiService {

    private TransaksiRepository transaksiRepository;
    private RekeningRepository rekeningRepository;

    @Autowired
    public TransaksiService(TransaksiRepository transaksiRepository,
                            RekeningRepository rekeningRepository) {
        this.transaksiRepository = transaksiRepository;
        this.rekeningRepository = rekeningRepository;
    }

    public boolean insertTransaksi(int noRekening ,TransaksiInsertDto insertDto) {
        Rekening rekening = rekeningRepository.findById(noRekening).get();
        Transaksi transaksi = new Transaksi(rekening,insertDto.insertTransaksi());
        transaksiRepository.save(transaksi);
        return true;
    }

    public boolean deleteTransaksi(int idTransaksi){
        transaksiRepository.deleteById(idTransaksi);
        return true;
    }

    public List<TransaksiHeaderDto> getTransaksiByDate(LocalDate tanggalTransaksi) {
        return TransaksiHeaderDto.toList(transaksiRepository.getTransaksiByDate(tanggalTransaksi));
    }

    public List<TransaksiHeaderDto> getTransaksiByNoRekening(int noRekening) {
        return TransaksiHeaderDto.toList(transaksiRepository.getTransaksiByNoRekening(noRekening));
    }


}
