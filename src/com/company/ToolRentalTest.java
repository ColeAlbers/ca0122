package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class ToolRentalTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void test1() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Discount must be between 0 and 100. Please try again.");

        String consoleInput = """
                JAKR
                5
                101%
                09/03/2015
                """;

        ToolRental.checkout(new Scanner(new ByteArrayInputStream(consoleInput.getBytes(StandardCharsets.UTF_8))), System.out);
    }

    @Test
    public void test2() throws Exception {
        String consoleInput = """
                LADW
                3
                10%
                07/02/2020
                """;
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

        ToolRental.checkout(new Scanner(new ByteArrayInputStream(consoleInput.getBytes(StandardCharsets.UTF_8))), new PrintStream(consoleOutput));

        assertTrue(consoleOutput.toString().contains("Tool Code: LADW"));
        assertTrue(consoleOutput.toString().contains("Tool Type: Ladder"));
        assertTrue(consoleOutput.toString().contains("Tool Brand: Werner"));
        assertTrue(consoleOutput.toString().contains("Rental Days: 3"));
        assertTrue(consoleOutput.toString().contains("Checkout Date: 07/02/2020"));
        assertTrue(consoleOutput.toString().contains("Due Date: 07/05/2020"));
        assertTrue(consoleOutput.toString().contains("Daily Rental Charge: $1.99"));
        assertTrue(consoleOutput.toString().contains("Charge Days: 2"));
        assertTrue(consoleOutput.toString().contains("Pre-Discount Charge: $3.98"));
        assertTrue(consoleOutput.toString().contains("Discount Percent: 10%"));
        assertTrue(consoleOutput.toString().contains("Discount Amount: $0.40"));
        assertTrue(consoleOutput.toString().contains("Final Charge: $3.58"));
    }

    @Test
    public void test3() throws Exception {
        String consoleInput = """
                CHNS
                5
                25%
                07/02/2015
                """;
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

        ToolRental.checkout(new Scanner(new ByteArrayInputStream(consoleInput.getBytes(StandardCharsets.UTF_8))), new PrintStream(consoleOutput));

        assertTrue(consoleOutput.toString().contains("Tool Code: CHNS"));
        assertTrue(consoleOutput.toString().contains("Tool Type: Chainsaw"));
        assertTrue(consoleOutput.toString().contains("Tool Brand: Stihl"));
        assertTrue(consoleOutput.toString().contains("Rental Days: 5"));
        assertTrue(consoleOutput.toString().contains("Checkout Date: 07/02/2015"));
        assertTrue(consoleOutput.toString().contains("Due Date: 07/07/2015"));
        assertTrue(consoleOutput.toString().contains("Daily Rental Charge: $1.49"));
        assertTrue(consoleOutput.toString().contains("Charge Days: 3"));
        assertTrue(consoleOutput.toString().contains("Pre-Discount Charge: $4.47"));
        assertTrue(consoleOutput.toString().contains("Discount Percent: 25%"));
        assertTrue(consoleOutput.toString().contains("Discount Amount: $1.12"));
        assertTrue(consoleOutput.toString().contains("Final Charge: $3.35"));
    }

    @Test
    public void test4() throws Exception {
        String consoleInput = """
                JAKD
                6
                0%
                09/03/2015
                """;
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

        ToolRental.checkout(new Scanner(new ByteArrayInputStream(consoleInput.getBytes(StandardCharsets.UTF_8))), new PrintStream(consoleOutput));

        assertTrue(consoleOutput.toString().contains("Tool Code: JAKD"));
        assertTrue(consoleOutput.toString().contains("Tool Type: Jackhammer"));
        assertTrue(consoleOutput.toString().contains("Tool Brand: DeWalt"));
        assertTrue(consoleOutput.toString().contains("Rental Days: 6"));
        assertTrue(consoleOutput.toString().contains("Checkout Date: 09/03/2015"));
        assertTrue(consoleOutput.toString().contains("Due Date: 09/09/2015"));
        assertTrue(consoleOutput.toString().contains("Daily Rental Charge: $2.99"));
        assertTrue(consoleOutput.toString().contains("Charge Days: 3"));
        assertTrue(consoleOutput.toString().contains("Pre-Discount Charge: $8.97"));
        assertTrue(consoleOutput.toString().contains("Discount Percent: 0%"));
        assertTrue(consoleOutput.toString().contains("Discount Amount: $0.00"));
        assertTrue(consoleOutput.toString().contains("Final Charge: $8.97"));
    }

    @Test
    public void test5() throws Exception {
        String consoleInput = """
                JAKR
                9
                0%
                07/02/2015
                """;
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

        ToolRental.checkout(new Scanner(new ByteArrayInputStream(consoleInput.getBytes(StandardCharsets.UTF_8))), new PrintStream(consoleOutput));

        assertTrue(consoleOutput.toString().contains("Tool Code: JAKR"));
        assertTrue(consoleOutput.toString().contains("Tool Type: Jackhammer"));
        assertTrue(consoleOutput.toString().contains("Tool Brand: Ridgid"));
        assertTrue(consoleOutput.toString().contains("Rental Days: 9"));
        assertTrue(consoleOutput.toString().contains("Checkout Date: 07/02/2015"));
        assertTrue(consoleOutput.toString().contains("Due Date: 07/11/2015"));
        assertTrue(consoleOutput.toString().contains("Daily Rental Charge: $2.99"));
        assertTrue(consoleOutput.toString().contains("Charge Days: 5"));
        assertTrue(consoleOutput.toString().contains("Pre-Discount Charge: $14.95"));
        assertTrue(consoleOutput.toString().contains("Discount Percent: 0%"));
        assertTrue(consoleOutput.toString().contains("Discount Amount: $0.00"));
        assertTrue(consoleOutput.toString().contains("Final Charge: $14.95"));
    }

    @Test
    public void test6() throws Exception {
        String consoleInput = """
                JAKR
                4
                50%
                07/02/2020
                """;
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

        ToolRental.checkout(new Scanner(new ByteArrayInputStream(consoleInput.getBytes(StandardCharsets.UTF_8))), new PrintStream(consoleOutput));

        assertTrue(consoleOutput.toString().contains("Tool Code: JAKR"));
        assertTrue(consoleOutput.toString().contains("Tool Type: Jackhammer"));
        assertTrue(consoleOutput.toString().contains("Tool Brand: Ridgid"));
        assertTrue(consoleOutput.toString().contains("Rental Days: 4"));
        assertTrue(consoleOutput.toString().contains("Checkout Date: 07/02/2020"));
        assertTrue(consoleOutput.toString().contains("Due Date: 07/06/2020"));
        assertTrue(consoleOutput.toString().contains("Daily Rental Charge: $2.99"));
        assertTrue(consoleOutput.toString().contains("Charge Days: 1"));
        assertTrue(consoleOutput.toString().contains("Pre-Discount Charge: $2.99"));
        assertTrue(consoleOutput.toString().contains("Discount Percent: 50%"));
        assertTrue(consoleOutput.toString().contains("Discount Amount: $1.50"));
        assertTrue(consoleOutput.toString().contains("Final Charge: $1.49"));
    }
}
