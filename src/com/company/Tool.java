package com.company;

public abstract class Tool {
    public final String code;
    public final ToolBrand brand;
    public final double dailyCharge;
    public final boolean weekdayCharge;
    public final boolean weekendCharge;
    public final boolean holidayCharge;

    public Tool(String code, ToolBrand brand, double dailyCharge, boolean weekdayCharge, boolean weekendCharge, boolean holidayCharge) {
        this.code = code;
        this.brand = brand;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }
}
