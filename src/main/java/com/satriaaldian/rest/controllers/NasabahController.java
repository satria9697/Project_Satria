package com.satriaaldian.rest.controllers;

import com.satriaaldian.rest.RestResponse;
import com.satriaaldian.rest.dtos.nasabah.NasabahHeaderDto;
import com.satriaaldian.rest.dtos.nasabah.NasabahUpsertDto;
import com.satriaaldian.rest.services.NasabahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nasabah")
public class NasabahController {
    @Autowired
    private NasabahService service;

    @GetMapping("find-all")
    public ResponseEntity<RestResponse<List<NasabahHeaderDto>>> findAllNasabah(){
        return ResponseEntity.ok().body(
                new RestResponse<>(
                        service.findAllNasabah(),
                        "Berhasil Menampilkan Semua Data Nasabah",
                        "200"));
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<RestResponse<NasabahHeaderDto>> findNasabahById(@PathVariable int id){
        return ResponseEntity.ok().body(
                new RestResponse<>(
                        service.findNasabahById(id),
                        "Berhasil Menampilkan Data Nasabah Dengan ID: " +id,
                        "200"));
    }

    @PostMapping("insert")
    public ResponseEntity<RestResponse<Boolean>> insertNasabah(@RequestBody NasabahUpsertDto newNasabah){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNasabah(newNasabah),
                        "Berhasil Membuat Data Nasabah Baru",
                        "201"),
                HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<RestResponse<Boolean>> updateNasabah (@PathVariable int id, @RequestBody NasabahUpsertDto updateDto){
        return new ResponseEntity<>(
                new RestResponse<>( service.updateNasabah(id, updateDto),
                        "Berhasil Mengupdate Data Nasabah Dengan ID: "+id,
                        "200"),
                HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<RestResponse<Boolean>> deleteNasabah (@PathVariable int id){
        return new ResponseEntity<>(
                new RestResponse<>(  service.deleteNasabah(id),
                        "Berhasil Menghapus Data Nasabah Dengan ID: " + id,
                        "200"),
                HttpStatus.OK);
    }
}
