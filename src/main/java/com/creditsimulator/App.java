package com.creditsimulator;

import com.creditsimulator.service.CreditService;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws IOException {
        CreditService creditService = new CreditService();
        if (args.length > 0) {
            creditService.calculationLoanFromFile(args[0]);
        } else {
            creditService.calculationLoanFromApi();
        }
    }
}
