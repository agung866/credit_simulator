package com.creditsimulator.service;

import com.creditsimulator.model.RequestCredit;
import com.creditsimulator.util.Constans;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class CreditService {

    private final URL url;

    public CreditService(URL url) {
        this.url = url;
    }

    public CreditService() throws IOException {
        this(new URL("https://run.mocky.io/v3/32cf01c9-8dd6-4629-a595-434a05c3f04d"));
    }

    public void calculationLoanFromFile(BufferedReader reader) throws IOException {

        String line;
        RequestCredit map = new RequestCredit();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 6) {
                map = contractReqFromFile(parts);
            }
        }
        boolean isValid = validationRequest(map);
        if (isValid) {
            calculateInstallment(map.getPrincipalLoan(), map.getTenor(), map.getVehicleType());
        }
    }

    private RequestCredit contractReqFromFile(String[] parts) {
        RequestCredit requestCredit = new RequestCredit();
        requestCredit.setVehicleType(parts[0]);
        requestCredit.setVehicleCondition(parts[1]);
        requestCredit.setYearOfVehicle(Integer.parseInt(parts[2]));
        requestCredit.setPrincipalLoan(new BigDecimal(parts[3]));
        requestCredit.setTenor(Integer.parseInt(parts[4]));
        requestCredit.setDownPayment(Integer.parseInt(parts[5]));
        return requestCredit;
    }

    public void calculationLoanFromApi() throws IOException {

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder res = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            res.append(line);
        }
        reader.close();
        RequestCredit mapResp = constructRequest(res.toString());
        boolean isValid = validationRequest(mapResp);
        if (isValid) {
            calculateInstallment(mapResp.getPrincipalLoan(), mapResp.getTenor(), mapResp.getVehicleType());
        }
    }

    private RequestCredit constructRequest(String json) {
        Map<String, String> map = new HashMap<>();
        RequestCredit req = new RequestCredit();
        json = json.trim().replaceAll("[{}\\s\"]", "");
        String[] pairs = json.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                map.put(keyValue[0], keyValue[1]);
            }
        }
        req.setVehicleType(map.get("vehicleType"));
        req.setVehicleCondition(map.get("vehicleCondition"));
        req.setYearOfVehicle(Integer.parseInt(map.get("yearOfVehicle")));
        req.setTenor(Integer.parseInt(map.get("tenor")));
        req.setDownPayment(Integer.parseInt(map.get("downPayment")));
        req.setPrincipalLoan(new BigDecimal(map.get("loanAmount")));

        return req;
    }

    private boolean validationRequest(RequestCredit requestCredit) {
        if (!requestCredit.getVehicleType().equalsIgnoreCase(Constans.MOBIL)
                && !requestCredit.getVehicleType().equalsIgnoreCase(Constans.MOTOR)) {
            System.err.println("ERROR : Vehicle Type is Wrong Please Input Mobil/Motor");
            return false;
        }
        if (!validateYearOfVehicle(requestCredit.getYearOfVehicle())) {
            System.err.println("ERROR : Wrong Input Year Please Input With format yyyy, ex : 2024");
            return false;
        }

        if ((!requestCredit.getVehicleCondition().equalsIgnoreCase(Constans.BARU))
                && (!requestCredit.getVehicleCondition().equalsIgnoreCase(Constans.BEKAS))) {
            System.err.println("ERROR : Condition Type is Wrong Please Input BARU/BEKAS");
            return false;
        }
        if (requestCredit.getPrincipalLoan().compareTo(new BigDecimal(1000000000)) >= 0) {
            System.err.println("ERROR : Loans can only be less or equal to 1 billion");
            return false;
        }
        if (requestCredit.getTenor() > 6) {
            System.err.println("ERROR : Tenor Cannot  be less then 1 years OR Tenor Cannot more than 6 years.");
            return false;
        }
        int yearNewVehicle = ZonedDateTime.now().minusYears(1).getYear();
        if (requestCredit.getVehicleCondition().equalsIgnoreCase(Constans.BARU) && requestCredit.getYearOfVehicle() < yearNewVehicle) {
            System.err.println("ERROR : Vehicle is not new condition, Please input new condition with year less than currentYear - 1");
            return false;
        }
        if (requestCredit.getVehicleType().equalsIgnoreCase(Constans.MOBIL) && requestCredit.getDownPayment() < 35) {
            System.err.println("ERROR : Car Down Payment cannot be less than 35%");
            return false;
        } else if (requestCredit.getVehicleType().equalsIgnoreCase(Constans.MOTOR) && requestCredit.getDownPayment() < 25) {
            System.err.println("ERROR : Motorbike Down Payment cannot be less than 25%");
            return false;
        }
        return true;
    }

    private boolean validateYearOfVehicle(int tahunKendaraan) {
        return String.valueOf(tahunKendaraan).length() == 4;
    }

    private static void calculateInstallment(BigDecimal principalLoan, int tenor, String vehicleType) {
        BigDecimal monthlyInstallment;
        BigDecimal rate = vehicleType.equalsIgnoreCase("Mobil") ? new BigDecimal("8.0") : new BigDecimal("9.0");
        BigDecimal installmentYearly;
        BigDecimal totalMonthlyInstallments = BigDecimal.ZERO;


        for (int year = 1; year <= tenor; year++) {
            if (year > 1) {
                if (year % 2 == 0) {
                    rate = rate.add(new BigDecimal("0.1"));
                } else {
                    rate = rate.add(new BigDecimal("0.5"));
                }
            }

            principalLoan = (principalLoan.multiply(rate.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP))).add(principalLoan);
            BigDecimal devisor = (BigDecimal.valueOf(12).multiply(new BigDecimal(tenor)))
                    .subtract(totalMonthlyInstallments);
            monthlyInstallment = principalLoan.divide(devisor, 2, RoundingMode.HALF_UP);

            System.out.printf("Tahun %d : Rp. %, .2f/bln, Suku Bunga : %.1f%%%n", year, monthlyInstallment, rate);

            installmentYearly = monthlyInstallment.multiply(new BigDecimal(12));
            totalMonthlyInstallments = totalMonthlyInstallments.add(new BigDecimal(12));
            principalLoan = principalLoan.subtract(installmentYearly);

        }
    }
}
