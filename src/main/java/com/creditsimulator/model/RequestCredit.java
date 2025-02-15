package com.creditsimulator.model;

import java.math.BigDecimal;

public class RequestCredit {
    JenisKendaraan jenisKendaraan;
    KondisiKendaraan kondisiKendaraan;

    int tahunKendaraan;
    BigDecimal jumlahPinjamanTotal;
    int tenor;
    BigDecimal jumlahDp;
    BigDecimal jumlahCicilanPerbulan;
}
