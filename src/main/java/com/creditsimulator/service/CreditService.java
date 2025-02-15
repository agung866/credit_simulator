package com.creditsimulator.service;

import com.creditsimulator.model.KondisiKendaraan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;

public class CreditService {



    private boolean validateThnKendaraan(int tahunKendaraan){
        return String.valueOf(tahunKendaraan).length()==4;
    }
    private void validateKendaraan(String kondisiKendaraan, int tahunKendaraan) {
        int thnKendaraanBaru = ZonedDateTime.now().minusYears(1).getYear();
        if (kondisiKendaraan.equalsIgnoreCase("BARU") && thnKendaraanBaru >= tahunKendaraan) {
            System.out.println("Vehicle is not new condition");
        }
    }

    private void validateTenorPinjaman(int tenorPinjaman) {
        if (tenorPinjaman < 1 || tenorPinjaman > 6) {
            System.out.println("Tenor Cannot  be less then 1 years OR Tenor Cannot more than 6 years.");
        }
    }

    private void validateDp(BigDecimal totalPinjaman, String kondisiKendaraan) {
        BigDecimal totalPinjamanKendaraanBaru = totalPinjaman.multiply(new BigDecimal(35).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
        BigDecimal totalPinjamanKendaraanLama = totalPinjaman.multiply(new BigDecimal(25).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
        if (kondisiKendaraan.equalsIgnoreCase("BARU") && totalPinjaman.compareTo(totalPinjamanKendaraanBaru) < 0) {
            System.out.println("Total Dp mush be more then 35% total Loan");
        } else if (kondisiKendaraan.equalsIgnoreCase("LAMA") && totalPinjaman.compareTo(totalPinjamanKendaraanLama) < 0) {
            System.out.println("Total Dp mush be more then 25% total Loan");
        }
    }

    private double totalSukuBunga(String jenisKendaraan, int tenor) {
        double totalSukuBunga = 0;
        if (jenisKendaraan.equalsIgnoreCase("MOBIL")) {
            totalSukuBunga = 8.0;
        } else {
            totalSukuBunga = 9.0;
        }

        for (int i = 2; i <= tenor; i++) {
            totalSukuBunga += 0.1;
            if (i % 2 == 0) {
                totalSukuBunga += 0.5;
            }
        }
        return totalSukuBunga;
    }
}
