package com.creditsimulator.model;

import java.math.BigDecimal;

public class RequestCredit {
    String vehicleType;
    String vehicleCondition;
    int yearOfVehicle;
    BigDecimal principalLoan;
    int tenor;
    int downPayment;
    public RequestCredit() {
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public int getYearOfVehicle() {
        return yearOfVehicle;
    }

    public void setYearOfVehicle(int yearOfVehicle) {
        this.yearOfVehicle = yearOfVehicle;
    }

    public BigDecimal getPrincipalLoan() {
        return principalLoan;
    }

    public void setPrincipalLoan(BigDecimal principalLoan) {
        this.principalLoan = principalLoan;
    }

    public int getTenor() {
        return tenor;
    }

    public void setTenor(int tenor) {
        this.tenor = tenor;
    }

    public int getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }
}
