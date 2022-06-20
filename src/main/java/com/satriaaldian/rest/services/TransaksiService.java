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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

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
        Transaksi transaksi = new Transaksi(
                rekening,
                insertDto.getNoTransaksi(),
                insertDto.getJenisTransaksi(),
                LocalDateTime.now(),
                insertDto.getJumlahTransaksi());
        transaksiRepository.save(transaksi);

        int saldoTerbaru = insertDto.getJumlahTransaksi();
        if(insertDto.getJenisTransaksi().equals("kredit")){
            saldoTerbaru = rekening.getSaldo() - insertDto.getJumlahTransaksi();
        } else if(insertDto.getJenisTransaksi().equals("debit")){
            saldoTerbaru = rekening.getSaldo() + insertDto.getJumlahTransaksi();
        }
        rekening.setSaldo(saldoTerbaru);
        rekeningRepository.save(rekening);
        return true;
    }
    public String generateNoTransaksi() {
        int number = 0;
        int noTransaksi = number+1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String tgltrx = LocalDate.now().format(formatter);
        return (tgltrx+String.format("%06d",noTransaksi));

    }


    public boolean deleteTransaksi(int idTransaksi){
        transaksiRepository.deleteById(idTransaksi);
        return true;
    }

    public List<TransaksiHeaderDto> getTransaksiByDate(LocalDateTime tanggalTransaksi) {
        return TransaksiHeaderDto.toList(transaksiRepository.getTransaksiByDate(tanggalTransaksi));
    }

    public List<TransaksiHeaderDto> getTransaksiByNoRekening(int noRekening) {
        return TransaksiHeaderDto.toList(transaksiRepository.getTransaksiByNoRekening(noRekening));
    }


}
