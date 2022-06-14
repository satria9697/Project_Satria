package com.satriaaldian.rest.services;

import com.satriaaldian.rest.dtos.nasabah.NasabahHeaderDto;
import com.satriaaldian.rest.dtos.nasabah.NasabahUpsertDto;
import com.satriaaldian.rest.dtos.nasabah.NasabahUpdateDto;
import com.satriaaldian.rest.models.Nasabah;
import com.satriaaldian.rest.repositories.NasabahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NasabahService {
    @Autowired
    private NasabahRepository nasabahRepository;

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
    public Boolean insertNasabah(NasabahUpsertDto newNasabah){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Nasabah nasabah = new Nasabah(
                newNasabah.getNamaDepan(),
                newNasabah.getNamaBelakang(),
                newNasabah.getTempatLahir(),
                LocalDate.parse(newNasabah.getTanggalLahir(),date),
                newNasabah.getJenisKelamin(),
                newNasabah.getAlamat(),
                newNasabah.getNoHandphone()
        );
        if(nasabah.getTanggalLahir().isAfter(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tanggal lahir tidak boleh melebihi hari ini");
        }
        if (nasabah.getJenisKelamin().equals("laki-laki")|| nasabah.getJenisKelamin().equals("perempuan")) {
            nasabah.getJenisKelamin();
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jenis kelamin hanya bisa 'laki-laki' dan 'perempuan'");
        }
        nasabahRepository.save(nasabah);
        return true;
    }

    // Update Data Nasabah
    public Boolean updateNasabah(int id, NasabahUpsertDto updateDto){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Nasabah oldNasabah = nasabahRepository.getById(id);
        oldNasabah.setNamaDepan(updateDto.getNamaDepan());
        oldNasabah.setNamaBelakang(updateDto.getNamaBelakang());
        oldNasabah.setTempatLahir(updateDto.getTempatLahir());
        oldNasabah.setTanggalLahir(LocalDate.parse(updateDto.getTanggalLahir(),date));
        if(oldNasabah.getTanggalLahir().isAfter(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tanggal lahir tidak boleh melebihi hari ini");
        }
        oldNasabah.setJenisKelamin(updateDto.getJenisKelamin());
        if (oldNasabah.getJenisKelamin().equals("laki-laki")|| oldNasabah.getJenisKelamin().equals("perempuan")) {
            oldNasabah.getJenisKelamin();
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jenis kelamin hanya bisa 'laki-laki' dan 'perempuan'");
        }
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
