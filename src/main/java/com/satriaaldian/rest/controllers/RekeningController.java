package com.satriaaldian.rest.controllers;

import com.satriaaldian.rest.RestResponse;
import com.satriaaldian.rest.dtos.rekening.RekeningDto;
import com.satriaaldian.rest.dtos.rekening.RekeningInsertDto;
import com.satriaaldian.rest.services.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rekening")
public class RekeningController {

    @Autowired
    private RekeningService service;

    @GetMapping("cek-nasabah/{noRekening}")
    public ResponseEntity<RestResponse<RekeningDto>> cekRekeningNasabah(@PathVariable int noRekening){
        return new ResponseEntity<>(
                new RestResponse<>(
                        service.cekRekeningNasabah(noRekening),
                        "Berhasil Ambil Data Nasabah Dengan Nomor Rekening : " + noRekening,
                        "200"),
                HttpStatus.OK);
    }
    @PostMapping("insert/{idNasabah}")
    public ResponseEntity<RestResponse<Boolean>> insertRekening(@PathVariable int idNasabah,RekeningInsertDto insertDto){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertRekening(idNasabah, insertDto),
                        "Berhasil Membuat Entitas",
                        "201"),
                HttpStatus.CREATED);
    }
}
