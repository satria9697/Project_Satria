package com.satriaaldian.rest.services;

import com.satriaaldian.rest.dtos.nasabah.NasabahHeaderDto;
import com.satriaaldian.rest.dtos.nasabah.NasabahUpsertDto;
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
        if (!nasabahRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nasabah tidak ditemukan");
        }else {
            Nasabah nasabah = nasabahRepository.getById(id);
            return NasabahHeaderDto.set(nasabah);
        }
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
        // validasi Insert Nasabah
        if(nasabah.getNamaDepan().matches("^[a-zA-Z]*$")) {
            nasabah.getNamaDepan();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nama Depan tidak boleh mengandung angka");
        }

        if(nasabah.getNamaBelakang().matches("^[a-zA-Z]*$")) {
            nasabah.getNamaBelakang();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nama Belakang tidak boleh mengandung angka");
        }

        if(nasabah.getTempatLahir().matches("^[a-zA-Z]*$")) {
            nasabah.getTempatLahir();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Tempat lahir tidak boleh mengandung angka");
        }

        if(nasabah.getTanggalLahir().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Tanggal lahir tidak boleh melebihi hari ini");
        }

        if (nasabah.getJenisKelamin().equals("laki-laki")||
                nasabah.getJenisKelamin().equals("perempuan")) {
            nasabah.getJenisKelamin();
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Jenis kelamin hanya bisa 'laki-laki' dan 'perempuan'");
        }

        if(nasabah.getNoHandphone().length() != 12 ||
                !nasabah.getNoHandphone().matches("^[a-zA-Z]*$")){
            nasabah.getNoHandphone();}
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nomor handphone harus 12 digit dan tidak boleh mengandung huruf");
        }

        nasabahRepository.save(nasabah);
        return true;
    }

    // Update Data Nasabah
    public Boolean updateNasabah(int id, NasabahUpsertDto updateDto){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Nasabah oldNasabah = nasabahRepository.getById(id);
        oldNasabah.setNamaDepan(updateDto.getNamaDepan());
        if(oldNasabah.getNamaDepan().matches("^[a-zA-Z]*$")) {
            oldNasabah.getNamaDepan();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nama Depan tidak boleh mengandung angka");
        }
        oldNasabah.setNamaBelakang(updateDto.getNamaBelakang());
        if(oldNasabah.getNamaBelakang().matches("^[a-zA-Z]*$")) {
            oldNasabah.getNamaBelakang();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nama Belakang tidak boleh mengandung angka");
        }
        oldNasabah.setTempatLahir(updateDto.getTempatLahir());
        if(oldNasabah.getTempatLahir().matches("^[a-zA-Z]*$")) {
            oldNasabah.getTempatLahir();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Tempat lahir tidak boleh mengandung angka");
        }
        oldNasabah.setTanggalLahir(LocalDate.parse(updateDto.getTanggalLahir(),date));
        if(oldNasabah.getTanggalLahir().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Tanggal lahir tidak boleh melebihi hari ini");
        }
        oldNasabah.setJenisKelamin(updateDto.getJenisKelamin());
        if (oldNasabah.getJenisKelamin().equals("laki-laki")|| oldNasabah.getJenisKelamin().equals("perempuan")) {
            oldNasabah.getJenisKelamin();
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jenis kelamin hanya bisa 'laki-laki' dan 'perempuan'");
        }
        oldNasabah.setAlamat(updateDto.getAlamat());
        oldNasabah.setNoHandphone(updateDto.getNoHandphone());
        if(oldNasabah.getNoHandphone().length() != 12 ||
                !oldNasabah.getNoHandphone().matches("^[a-zA-Z]*$")){
            oldNasabah.getNoHandphone();}
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nomor handphone harus 12 digit dan tidak boleh mengandung huruf");
        }
        nasabahRepository.save(oldNasabah);
        return true;
    }

    // Delete Nasabah dengan Nomor Id
    public Boolean deleteNasabah(int id){
        if (!nasabahRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nasabah tidak ditemukan");
        }else {
            nasabahRepository.deleteById(id);
        }
        return true;
    }


}
