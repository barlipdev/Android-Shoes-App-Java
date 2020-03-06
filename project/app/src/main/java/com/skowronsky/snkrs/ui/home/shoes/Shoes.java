package com.skowronsky.snkrs.ui.home.shoes;

public class Shoes {

    private String shoe_company;
    private String model;
    private int number;

    public void setModel(String model) {
        this.model = model;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setShoe_company(String shoe_company) {
        this.shoe_company = shoe_company;
    }

    public String getModel() {
        return model;
    }

    public int getNumber() {
        return number;
    }

    public String getShoe_company() {
        return shoe_company;
    }
}
