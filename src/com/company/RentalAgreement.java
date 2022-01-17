package com.company;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentalAgreement {
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    private static final List<Integer> WEEKENDS = Arrays.asList(Calendar.SATURDAY, Calendar.SUNDAY);
    private static final List<Integer> WEEKDAYS = Arrays.asList(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY);

    private final Tool tool;
    private final int numberOfRentalDays;
    private final int discount;
    private final Date checkoutDate;

    public RentalAgreement(Tool tool, int numberOfRentalDays, int discount, Date checkoutDate) {
        this.tool = tool;
        this.numberOfRentalDays = numberOfRentalDays;
        this.discount = discount;
        this.checkoutDate = checkoutDate;
    }

    public void print(PrintStream output) {
        output.println("----------");
        output.println("Tool Code: " + this.tool.code);
        output.println("Tool Type: " + this.tool.getClass().getSimpleName());
        output.println("Tool Brand: " + this.tool.brand);
        output.println("Rental Days: " + this.numberOfRentalDays);
        output.println("Checkout Date: " + DATE_FORMAT.format(this.checkoutDate));
        output.println("Due Date: " + DATE_FORMAT.format(this.determineDueDate()));
        output.println("Daily Rental Charge: " + CURRENCY_FORMAT.format(this.tool.dailyCharge));

        int chargeDays = this.determineChargeDays();
        output.println("Charge Days: " + chargeDays);

        double preDiscountCharge = this.determinePreDiscountCharge(chargeDays);
        output.println("Pre-Discount Charge: " + CURRENCY_FORMAT.format(preDiscountCharge));

        output.println("Discount Percent: " + this.discount + '%');

        double discountAmount = this.determineDiscountAmount(preDiscountCharge);
        output.println("Discount Amount: " + CURRENCY_FORMAT.format(discountAmount));

        output.println("Final Charge: " + CURRENCY_FORMAT.format(preDiscountCharge - discountAmount));
        output.println("----------");
    }

    private Date determineDueDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.checkoutDate);
        calendar.add(Calendar.DATE, this.numberOfRentalDays);
        return calendar.getTime();
    }

    private int determineChargeDays() {
        int chargeDays = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.checkoutDate);
        for (int i = 0; i < this.numberOfRentalDays; i++) {
            calendar.add(Calendar.DATE, 1);
            if (this.shouldChargeDay(calendar)) {
                chargeDays++;
            }
        }

        return chargeDays;
    }

    private double determinePreDiscountCharge(int chargeDays) {
        return new BigDecimal(this.tool.dailyCharge * chargeDays).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private double determineDiscountAmount(double preDiscountCharge) {
        return new BigDecimal(preDiscountCharge * (this.discount / 100.0)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private boolean shouldChargeDay(Calendar calendar) {
        return ((this.tool.weekdayCharge && this.isWeekday(calendar)) || (this.tool.weekendCharge && this.isWeekend(calendar))) && (this.tool.holidayCharge || !this.isHoliday(calendar));
    }

    private boolean isWeekday(Calendar calendar) {
        return WEEKDAYS.contains(calendar.get(Calendar.DAY_OF_WEEK));
    }

    private boolean isWeekend(Calendar calendar) {
        return WEEKENDS.contains(calendar.get(Calendar.DAY_OF_WEEK));
    }

    private boolean isHoliday(Calendar calendar) {
        return this.isIndependenceDay(calendar) || this.isLaborDay(calendar);
    }

    private boolean isIndependenceDay(Calendar calendar) {
        int observedIndependenceDayDate = 4;
        Calendar calendarToDetermineObservedIndependenceDayDate = Calendar.getInstance();
        calendarToDetermineObservedIndependenceDayDate.set(calendar.get(Calendar.YEAR), Calendar.JULY, 4);
        if (calendarToDetermineObservedIndependenceDayDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            observedIndependenceDayDate = 3;
        } else if (calendarToDetermineObservedIndependenceDayDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            observedIndependenceDayDate = 5;
        }
        return calendar.get(Calendar.MONTH) == Calendar.JULY && (calendar.get(Calendar.DAY_OF_MONTH) == observedIndependenceDayDate);
    }

    private boolean isLaborDay(Calendar calendar) {
        return calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER && calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1 && calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }
}
