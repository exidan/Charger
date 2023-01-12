package com.pe.charger;

public class Current {
    Integer currentValue;

    public Integer getCur() {
        return currentValue;
    }

    public Current() {
        this.currentValue = 0;
    }
    public Current(Integer cur) {
        this.currentValue = cur;
    }

    public void setCur(Integer cur) {
        this.currentValue = cur;
    }

    @Override
    public String toString() {
        return "Current{" +
                "cur=" + currentValue +
                '}';
    }

}
