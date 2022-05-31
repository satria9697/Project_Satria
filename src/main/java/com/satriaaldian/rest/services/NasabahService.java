package com.satriaaldian.rest.services;

import com.satriaaldian.rest.dtos.nasabah.NasabahHeaderDto;
import com.satriaaldian.rest.dtos.nasabah.NasabahInsertDto;
import com.satriaaldian.rest.dtos.nasabah.NasabahUpdateDto;
import com.satriaaldian.rest.models.Nasabah;
import com.satriaaldian.rest.repositories.NasabahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NasabahService {

    private NasabahRepository nasabahRepository;

    @Autowired
    public NasabahService(NasabahRepository nasabahRepository) {
        this.nasabahRepository = nasabahRepository;
    }

    // Mencari Semua Nasabah
    public List<NasabahHeaderDto> findAllNasabah(){
        return NasabahHeaderDto.toList(nasabahRepository.findAll());
    }
    // Mencari Nasabah Dengan nomor ID
    public NasabahHeaderDto findNasabahById(int id){
        Nasabah nasabah = nasabahRepository.getById(id);
        return NasabahHeaderDto.set(nasabah);
    }

    // Memasukan Data Nasabah Baru
    public Boolean insertNasabah(NasabahInsertDto newNasabah){
        Nasabah nasabah = newNasabah.insertNasabah();
        nasabahRepository.save(nasabah);
        return true;
    }

    // Update Data Nasabah
    public Boolean updateNasabah(int id, NasabahUpdateDto updateDto){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Nasabah oldNasabah = nasabahRepository.getById(id);
        oldNasabah.setNamaDepan(updateDto.getNamaDepan());
        oldNasabah.setNamaBelakang(updateDto.getNamaBelakang());
        oldNasabah.setTempatLahir(updateDto.getTempatLahir());
        oldNasabah.setTanggalLahir(LocalDate.parse(updateDto.getTanggalLahir(),date));
        oldNasabah.setJenisKelamin(updateDto.getJenisKelamin());
        oldNasabah.setAlamat(updateDto.getAlamat());
        oldNasabah.setNoHandphone(updateDto.getNoHandphone());
        nasabahRepository.save(oldNasabah);
        return true;
    }

    // Delete Nasabah dengan Nomor Id

    public Boolean deleteNasabah(int id){
        nasabahRepository.deleteById(id);
        return true;
    }

}
