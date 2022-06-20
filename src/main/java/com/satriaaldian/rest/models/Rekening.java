package com.satriaaldian.rest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rekening {
    @Id
    @Column(name = "no_rekening", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nasabah", nullable = false)
    private Nasabah idNasabah;

    @Column(name = "saldo", nullable = false)
    private Integer saldo;

    @OneToMany(mappedBy = "noRekening")
    private Set<Transaksi> transaksis = new LinkedHashSet<>();

//    public Rekening(Integer id, Integer saldo) {
//        this.id = id;
//        this.saldo = saldo;
//    }

    public Rekening(Nasabah nasabah, long noRekening, int saldo) {
        this.idNasabah = nasabah;
        this.id = noRekening;
        this.saldo = saldo;
    }
}