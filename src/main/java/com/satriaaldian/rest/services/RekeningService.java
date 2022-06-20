package com.satriaaldian.rest.services;

import com.satriaaldian.rest.dtos.rekening.RekeningDto;
import com.satriaaldian.rest.dtos.rekening.RekeningInsertDto;
import com.satriaaldian.rest.models.Nasabah;
import com.satriaaldian.rest.models.Rekening;
import com.satriaaldian.rest.repositories.NasabahRepository;
import com.satriaaldian.rest.repositories.RekeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RekeningService {
    @Autowired
    private RekeningRepository rekeningRepository;
    @Autowired
    private NasabahRepository nasabahRepository;


//    public RekeningService(RekeningRepository rekeningRepository, NasabahRepository nasabahRepository) {
//        this.rekeningRepository = rekeningRepository;
//        this.nasabahRepository = nasabahRepository;
//    }

    public RekeningDto cekRekeningNasabah(int noRekening){
        Rekening rekening = rekeningRepository.findById(noRekening).get();
        return RekeningDto.set(rekening);
    }
    //
    public Boolean insertRekening(int idnasabah, RekeningInsertDto insertDto) {
        Nasabah nasabah = nasabahRepository.findById(idnasabah).get();
        Rekening rekening = new Rekening(nasabah, generateNoRekening(), insertDto.getSaldo());
            rekeningRepository.save(rekening);
            return true;
    }
    public Long generateNoRekening(){
        Random rand = new Random();
        int maxDigit = 100000000;
        int noRekening = rand.nextInt(maxDigit);
        return Long.parseLong("1210"+String.format("%08d",noRekening));
    }


}
