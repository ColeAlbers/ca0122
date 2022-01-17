package com.company;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class ToolRental {
    private static final Map<String, Tool> AVAILABLE_TOOLS = Map.of(
            "CHNS", new Chainsaw("CHNS", ToolBrand.Stihl),
            "LADW", new Ladder("LADW", ToolBrand.Werner),
            "JAKD", new Jackhammer("JAKD", ToolBrand.DeWalt),
            "JAKR", new Jackhammer("JAKR", ToolBrand.Ridgid)
    );

    public static void main(String[] args) throws Exception {
        checkout(new Scanner(System.in), System.out);
    }

    public static void checkout(Scanner input, PrintStream output) throws Exception {
        String code = recieveCodeFromConsoleInput(input, output);
        int numberOfRentalDays = recieveNumberOfRentalDaysFromConsoleInput(input, output);
        int discount = recieveDiscountFromConsoleInput(input, output);
        Date checkoutDate = recieveCheckoutDateFromConsoleInput(input, output);

        new RentalAgreement(AVAILABLE_TOOLS.get(code), numberOfRentalDays, discount, checkoutDate).print(output);
    }

    private static String recieveCodeFromConsoleInput(Scanner input, PrintStream output) {
        output.print("Code: ");
        return input.nextLine();
    }

    private static int recieveNumberOfRentalDaysFromConsoleInput(Scanner input, PrintStream output) throws Exception {
        output.print("Number of Rental Days: ");
        int numberOfRentalDays = Integer.parseInt(input.nextLine());
        if (numberOfRentalDays <= 0) {
            throw new Exception("Number of Rental Days must be one or more. Please try again.");
        }
        return numberOfRentalDays;
    }

    private static int recieveDiscountFromConsoleInput(Scanner input, PrintStream output) throws Exception {
        output.print("Discount: ");
        int discount = Integer.parseInt(input.nextLine().replace("%", ""));
        if (discount < 0 || discount > 100) {
            throw new Exception("Discount must be between 0 and 100. Please try again.");
        }
        return discount;
    }

    private static Date recieveCheckoutDateFromConsoleInput(Scanner input, PrintStream output) throws ParseException {
        output.print("Checkout Date: ");
        return new SimpleDateFormat("MM/dd/yyyy").parse(input.nextLine());
    }
}
