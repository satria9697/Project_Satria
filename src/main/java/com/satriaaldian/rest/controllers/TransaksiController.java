package com.satriaaldian.rest.controllers;

import com.satriaaldian.rest.RestResponse;
import com.satriaaldian.rest.dtos.transaksi.TransaksiHeaderDto;
import com.satriaaldian.rest.dtos.transaksi.TransaksiInsertDto;
import com.satriaaldian.rest.services.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("transaksi")
public class TransaksiController {

    private TransaksiService service;

    @Autowired
    public TransaksiController(TransaksiService service) {
        this.service = service;
    }

    @PostMapping("insert/{noRekening}")
    public ResponseEntity<RestResponse<Boolean>> insertTransaksi(@PathVariable int noRekening, @RequestBody TransaksiInsertDto newTransaksi){
        return new ResponseEntity<>(
                new RestResponse<>( service.insertTransaksi(noRekening, newTransaksi),
                        "Berhasil Membuat Entitas",
                        "201"),
                HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{idTransaksi}")
    public ResponseEntity<RestResponse<Boolean>> deleteTransaksi(@PathVariable int idTransaksi){
        return new ResponseEntity<>(
                new RestResponse<>(service.deleteTransaksi(idTransaksi),
        "Berhasil Menghapus Entitas",
                "200"),
        HttpStatus.OK);
    }

    @GetMapping("date/{tanggalTransaksi}")
    public ResponseEntity<RestResponse<List<TransaksiHeaderDto>>> getTransaksiByDate(@PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate tanggalTransaksi){
        return ResponseEntity.ok().body(
                new RestResponse<>(
                        service.getTransaksiByDate(tanggalTransaksi),
                        "Berhasil Ambil Data",
                        "200"));
    }

    @GetMapping("no-rekening/{noRekening}")
    public ResponseEntity<RestResponse<List<TransaksiHeaderDto>>> getTransaksiByNoRekening(@PathVariable int noRekening){
        return ResponseEntity.ok().body(
                new RestResponse<>(
                        service.getTransaksiByNoRekening(noRekening),
                        "Berhasil Ambil Data",
                        "200"));
    }
}
