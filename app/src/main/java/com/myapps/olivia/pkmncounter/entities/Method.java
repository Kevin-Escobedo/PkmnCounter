package com.myapps.olivia.pkmncounter.entities;

import java.io.Serializable;

public class Method implements Serializable {

    private String name;
    private Number id;

    public Method() {
    }

    public Method(Number id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Number getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
