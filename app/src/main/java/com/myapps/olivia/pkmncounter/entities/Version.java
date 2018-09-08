package com.myapps.olivia.pkmncounter.entities;

import java.io.Serializable;

public class Version implements Serializable{

    private int number;
    private String name;

    public Version() {
    }

    public Version(String name) {
        this.name = name;
    }

    public Version(int number) {
        this.number = number;
    }

    public Version(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
