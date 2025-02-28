package com.creditsimulator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {
    @InjectMocks
    CreditService creditService;
    @Mock
    URL url;

    @BeforeEach
    public void setUp() throws Exception {
        creditService = new CreditService();
    }
    private static Stream<Arguments> argsSuccess(){
        return Stream.of(
                Arguments.of("{\n" +
                        "\"vehicleType\":\"Mobil\",\n" +
                        "\"vehicleCondition\":\"Bekas\",\n" +
                        "\"yearOfVehicle\":2023,\n" +
                        "\"loanAmount\":150000000,\n" +
                        "\"tenor\":6,\n" +
                        "\"downPayment\":40\n" +
                        "}"),
                Arguments.of(
                        "{\n" +
                                "\"vehicleType\":\"Motor\",\n" +
                                "\"vehicleCondition\":\"Bekas\",\n" +
                                "\"yearOfVehicle\":2023,\n" +
                                "\"loanAmount\":6000000,\n" +
                                "\"tenor\":5,\n" +
                                "\"downPayment\":30\n" +
                                "}"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("argsSuccess")
    void testCalculationLoanFromApiValidResponse(String args) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(args.getBytes());
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        when(mockConnection.getInputStream()).thenReturn(inputStream);

        URL mockUrl = mock(URL.class);
        when(mockUrl.openConnection()).thenReturn(mockConnection);

        creditService = new CreditService(mockUrl);


        assertDoesNotThrow(() -> creditService.calculationLoanFromApi());
    }


    @Test
    void testCalculationLoanFromFileValidFile() throws IOException {

        BufferedReader mockReader = Mockito.mock(BufferedReader.class);

        when(mockReader.readLine())
                .thenReturn("MOBIL|BEKAS|2020|200000000|6|35")
                .thenReturn(null);


        CreditService creditService = new CreditService();

        creditService.calculationLoanFromFile(mockReader);

        verify(mockReader, atLeast(2)).readLine();
    }
    private static Stream<Arguments> args(){
        return Stream.of(Arguments.of("{\n" +
                "\"vehicleType\":\"PESAWAT\",\n" +
                "\"vehicleCondition\":\"Bekas\",\n" +
                "\"yearOfVehicle\":2023,\n" +
                "\"loanAmount\":150000000,\n" +
                "\"tenor\":6,\n" +
                "\"downPayment\":35\n"),
                Arguments.of("{\n" +
                        "\"vehicleType\":\"MOBIL\",\n" +
                        "\"vehicleCondition\":\"LAMA\",\n" +
                        "\"yearOfVehicle\":2023,\n" +
                        "\"loanAmount\":150000000,\n" +
                        "\"tenor\":6,\n" +
                        "\"downPayment\":35\n"),
                Arguments.of("{\n" +
                        "\"vehicleType\":\"MOBIL\",\n" +
                        "\"vehicleCondition\":\"BARU\",\n" +
                        "\"yearOfVehicle\":2020,\n" +
                        "\"loanAmount\":150000000,\n" +
                        "\"tenor\":6,\n" +
                        "\"downPayment\":35\n"),
                Arguments.of("{\n" +
                        "\"vehicleType\":\"MOBIL\",\n" +
                        "\"vehicleCondition\":\"BARU\",\n" +
                        "\"yearOfVehicle\":20243,\n" +
                        "\"loanAmount\":150000000,\n" +
                        "\"tenor\":6,\n" +
                        "\"downPayment\":35\n"),
                Arguments.of("{\n" +
                        "\"vehicleType\":\"MOBIL\",\n" +
                        "\"vehicleCondition\":\"BARU\",\n" +
                        "\"yearOfVehicle\":2024,\n" +
                        "\"loanAmount\":150000000,\n" +
                        "\"tenor\":7,\n" +
                        "\"downPayment\":35\n"),
                Arguments.of("{\n" +
                        "\"vehicleType\":\"MOBIL\",\n" +
                        "\"vehicleCondition\":\"BARU\",\n" +
                        "\"yearOfVehicle\":2024,\n" +
                        "\"loanAmount\":150000000,\n" +
                        "\"tenor\":6,\n" +
                        "\"downPayment\":25\n"),
                Arguments.of("{\n" +
                        "\"vehicleType\":\"Motor\",\n" +
                        "\"vehicleCondition\":\"BARU\",\n" +
                        "\"yearOfVehicle\":2024,\n" +
                        "\"loanAmount\":150000000,\n" +
                        "\"tenor\":6,\n" +
                        "\"downPayment\":15\n"),
                Arguments.of("{\n" +
                        "\"vehicleType\":\"MOBIL\",\n" +
                        "\"vehicleCondition\":\"BARU\",\n" +
                        "\"yearOfVehicle\":2024,\n" +
                        "\"loanAmount\":1100000000,\n" +
                        "\"tenor\":6,\n" +
                        "\"downPayment\":35\n")

                );
    }
    @ParameterizedTest
    @MethodSource("args")
    void testCalculationLoanFromApiInvalidRes(String mockResp) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(mockResp.getBytes());
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        when(mockConnection.getInputStream()).thenReturn(inputStream);

        URL mockUrl = mock(URL.class);
        when(mockUrl.openConnection()).thenReturn(mockConnection);

        creditService = new CreditService(mockUrl);


        assertDoesNotThrow(() -> creditService.calculationLoanFromApi());
    }

}
