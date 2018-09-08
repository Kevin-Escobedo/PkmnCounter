package com.myapps.olivia.pkmncounter.entities;

import java.io.Serializable;

public class Method implements Serializable {

    private String name;
    private int rateWithCc;
    private int rateWithoutCc;

    public Method() {
    }

    public Method(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getRateWithCc() {
        return rateWithCc;
    }

    public void setRateWithCc(int rateWithCc) {
        this.rateWithCc = rateWithCc;
    }

    public int getRateWithoutCc() {
        return rateWithoutCc;
    }

    public void setRateWithoutCc(int rateWithoutCc) {
        this.rateWithoutCc = rateWithoutCc;
    }

    @Override
    public String toString() {
        return name;
    }
}
