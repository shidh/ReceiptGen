package com.receipt.Model;

import com.receipt.Constants;
import com.receipt.Util;

import java.math.BigDecimal;

/**
 * Created by allen on 26/11/15.
 */
public class Item {

    private String name;
    private int amount;
    private double price;
    private double totalTax;
    private boolean isExempt;
    private boolean isImported;
    private double shelfPrice;


    public Item(){
    }

    public Item(boolean isImported, int amount, double price, boolean isExempt, String name) {
        this.isImported = isImported;
        this.amount = amount;
        this.price = price;
        this.isExempt = isExempt;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isExempt() {
        return isExempt;
    }

    public void setExempt(boolean exempt) {
        isExempt = exempt;
    }

    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }


    public double getTotalTax(){
        int taxRate = 0;

        if(!isExempt) {
            taxRate += Constants.SALES_TAX_FOR_NOEXEMPT;
        }
        if(isImported) {
            taxRate += Constants.IMPORT_TAX_FOR_IMPORTED;
        }
        totalTax = taxRate * price /100.0;
        totalTax = Util.roundUpDouble(new BigDecimal(totalTax));

        return totalTax;
    }

    public double getShelfPrice(){
        shelfPrice = price + totalTax;
        return shelfPrice;
    }


    @Override
    public String toString() {
        return "Item{" +
                "isImported=" + isImported +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", isExempt=" + isExempt +
                '}';
    }

}
