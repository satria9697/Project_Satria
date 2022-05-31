package com.satriaaldian.rest.services;

import com.satriaaldian.rest.dtos.rekening.RekeningInsertDto;
import com.satriaaldian.rest.models.Nasabah;
import com.satriaaldian.rest.models.Rekening;
import com.satriaaldian.rest.repositories.NasabahRepository;
import com.satriaaldian.rest.repositories.RekeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RekeningService {

    private RekeningRepository rekeningRepository;
    private NasabahRepository nasabahRepository;

    @Autowired
    public RekeningService(RekeningRepository rekeningRepository, NasabahRepository nasabahRepository) {
        this.rekeningRepository = rekeningRepository;
        this.nasabahRepository = nasabahRepository;
    }

    //
    public Boolean insertRekening(int idnasabah, RekeningInsertDto insertDto) {
        Nasabah nasabah = nasabahRepository.findById(idnasabah).get();
        Rekening rekening = new Rekening(nasabah, insertDto.insertRekeningNasabah());
            rekeningRepository.save(rekening);
            return true;
    }


}
