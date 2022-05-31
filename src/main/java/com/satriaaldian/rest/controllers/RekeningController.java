package com.satriaaldian.rest.controllers;

import com.satriaaldian.rest.RestResponse;
import com.satriaaldian.rest.dtos.rekening.RekeningInsertDto;
import com.satriaaldian.rest.services.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rekening")
public class RekeningController {


    private RekeningService service;

    @Autowired
    public RekeningController(RekeningService service) {
        this.service = service;
    }

    @PostMapping("insert/{idNasabah}")
    public ResponseEntity<RestResponse<Boolean>> insertRekening(@PathVariable int idNasabah, @RequestBody RekeningInsertDto insertDto){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertRekening(idNasabah, insertDto),
                        "Berhasil Membuat Entitas",
                        "201"),
                HttpStatus.CREATED);
    }
}
