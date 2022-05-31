package com.satriaaldian.rest.controllers;

import com.satriaaldian.rest.RestResponse;
import com.satriaaldian.rest.dtos.nasabah.NasabahHeaderDto;
import com.satriaaldian.rest.dtos.nasabah.NasabahInsertDto;
import com.satriaaldian.rest.dtos.nasabah.NasabahUpdateDto;
import com.satriaaldian.rest.services.NasabahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("nasabah")
public class NasabahController {

    private NasabahService service;

    @Autowired
    public NasabahController(NasabahService service) {
        this.service = service;
    }

    @GetMapping("find-all")
    public ResponseEntity<RestResponse<List<NasabahHeaderDto>>> findAllNasabah(){
        return ResponseEntity.ok().body(
                new RestResponse<>(
                        service.findAllNasabah(),
                        "Berhasil Ambil Data",
                        "200"));
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<RestResponse<NasabahHeaderDto>> findNasabahById(@PathVariable int id){
        return ResponseEntity.ok().body(
                new RestResponse<>(
                        service.findNasabahById(id),
                        "Berhasil Ambil Data",
                        "200"));
    }

    @PostMapping("insert")
    public ResponseEntity<RestResponse<Boolean>> insertNasabah(@RequestBody NasabahInsertDto newNasabah){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNasabah(newNasabah),
                        "Berhasil Membuat Entitas",
                        "201"),
                HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<RestResponse<Boolean>> updateNasabah (@PathVariable int id, @RequestBody NasabahUpdateDto updateDto){
        return new ResponseEntity<>(
                new RestResponse<>( service.updateNasabah(id, updateDto),
                        "Berhasil Mengupdate Entitas",
                        "200"),
                HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<RestResponse<Boolean>> deleteNasabah (@PathVariable int id){
        return new ResponseEntity<>(
                new RestResponse<>(  service.deleteNasabah(id),
                        "Berhasil Menghapus Entitas",
                        "200"),
                HttpStatus.OK);
    }
}
