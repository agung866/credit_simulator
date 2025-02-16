package com.creditsimulator;

import com.creditsimulator.service.CreditService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {
        CreditService creditService = new CreditService();
        if (args.length > 0) {
            String fileName=args[0];
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            creditService.calculationLoanFromFile(reader);
        } else {
            creditService.calculationLoanFromApi();
        }
    }
}
